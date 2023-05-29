package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {
    public boolean existsByLicensePlate(String licencePlate);
    @Query("SELECT c FROM CarEntity c WHERE NOT EXISTS (SELECT 1 FROM ReservationEntity r WHERE r.car.id = c.id AND r.pickUpDate <= :endDate AND r.returnDate >= :startDate)")
    List<CarEntity> findAvailableCars(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM CarEntity c WHERE NOT EXISTS (SELECT 1 FROM ReservationEntity r WHERE r.car.id = c.id AND r.pickUpDate <= :endDate AND r.returnDate >= :startDate) AND (:brand = '' OR (:brand <> '' AND c.brand = :brand))")
    List<CarEntity> findAvailableCarsWithBrand(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("brand") String brand,
            Sort sort
    );
    @Query("SELECT DISTINCT c.brand FROM CarEntity c")
    List<String> findAllCarBrands();
    List<CarEntity> findAll(Specification<CarEntity> spec, Sort sort);
    @Query("SELECT c.brand as brand, count(c.brand) as count FROM CarEntity c inner join  ReservationEntity r on r.car.id = c.id group by c.brand")
    List<Tuple> getTheCountOfAllBrandsInReservations();
}
