package com.example.easywheelz.buisness.interfaces.car;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.FilterCarsByAvailabilityRequest;
import com.example.easywheelz.persistance.entities.CarEntity;

import java.time.LocalDate;
import java.util.List;

public interface FindAllAvailableCarsUseCase {
    List<Car> GetAllAvailableCarsInDateRange(FilterCarsByAvailabilityRequest request);
}
