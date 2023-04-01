package com.example.easywheelz.buisness.interfaces.car;

import com.example.easywheelz.domain.car.Car;

import java.util.List;

public interface GetCarUseCase {
    public Car getCar(long id);
    public List<Car> getAllCars();
}
