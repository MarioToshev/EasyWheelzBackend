package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    public boolean existsByLicensePlate(String licencePlate);

}
