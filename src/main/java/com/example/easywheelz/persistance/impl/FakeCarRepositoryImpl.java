package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@Repository
public class FakeCarRepositoryImpl implements CarRepository {

    private final List<CarEntity> cars;
    private static long NEXT_ID = 1;

    public FakeCarRepositoryImpl() {
        this.cars = new ArrayList<>();
    }

    @Override
    public boolean existsById(long carId) {
        return cars.stream()
                .anyMatch(carEntity -> Objects.equals(carEntity.getId(), carId));
    }

    @Override
    public CarEntity findById(long carId) {
        return cars.stream()
                .filter(carEntity -> Objects.equals(carEntity.getId(), carId))
                .findFirst().orElse(null);

    }

    @Override
    public CarEntity save(CarEntity car) {
        car.setId(NEXT_ID);
        NEXT_ID++;
        cars.add(car);
        return car;
    }

    @Override
    public void delete(long carId) {
        cars.remove(cars.stream().filter(carEntity -> Objects.equals(carEntity.getId(), carId)).findFirst().orElse(null));
    }

    @Override
    public List<CarEntity> findAll() {
        return Collections.unmodifiableList(cars);
    }

    @Override
    public void update(CarEntity car) {
        cars.stream()
                .filter(carEntity -> Objects.equals(carEntity.getId(), car.getId()))
                .findFirst()
                .ifPresent(carEntity -> {
                    carEntity.setId(car.getId());
                    carEntity.setAvailability(car.isAvailability());
                    carEntity.setColor(car.getColor());
                    carEntity.setBrand(car.getBrand());
                    carEntity.setModel(car.getModel());
                    carEntity.setLicensePlate(carEntity.getLicensePlate());
                });
    }
}
