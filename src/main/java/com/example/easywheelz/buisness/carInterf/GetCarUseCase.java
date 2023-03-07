package com.example.easywheelz.buisness.carInterf;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;

import java.util.List;

public interface GetCarUseCase {
    public Car getCar(long id);
    public List<Car> getAllCars();
}
