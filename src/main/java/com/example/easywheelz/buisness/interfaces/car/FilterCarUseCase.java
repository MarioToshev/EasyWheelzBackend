package com.example.easywheelz.buisness.interfaces.car;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.FilterRequest;

import java.util.List;

public interface FilterCarUseCase {

    List<Car> filterCars(FilterRequest request);
}
