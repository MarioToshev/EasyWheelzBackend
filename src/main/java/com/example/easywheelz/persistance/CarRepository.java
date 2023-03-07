package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.UserEntity;

import java.util.List;

public interface CarRepository {

    boolean existsById(long carId);
    CarEntity findById(long carId);
    CarEntity save(CarEntity car);
    void delete(long carId);
    List<CarEntity> findAll();
    void update(CarEntity car);
}
