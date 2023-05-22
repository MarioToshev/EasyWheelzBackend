package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>, JpaSpecificationExecutor<ReservationEntity>{
        @Query("SELECT COUNT(*) = 0 FROM ReservationEntity WHERE car.id = :carId AND pickUpDate <= :returnDate AND returnDate >= :pickUpDate")
        boolean isCarUnavailableBetweenDates(@Param("carId") Long carId, @Param("pickUpDate") LocalDate pickUpDate, @Param("returnDate") LocalDate returnDate);
        @Query("SELECT r FROM ReservationEntity r WHERE r.car.id = :carId AND r.pickUpDate <= :returnDate AND r.returnDate >= :pickUpDate")
        List<ReservationEntity> findOverlappingReservations(@Param("carId") long carId, @Param("pickUpDate") LocalDate pickUpDate, @Param("returnDate") LocalDate returnDate);
        List<ReservationEntity> findAllByCustomerId(Long userId);
}
