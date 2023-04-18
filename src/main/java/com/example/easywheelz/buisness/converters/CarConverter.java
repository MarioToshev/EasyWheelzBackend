package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.persistance.entities.CarEntity;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarConverter {
    public CarEntity convert(@NotNull CreateCarRequest request){
        return CarEntity.builder()
                .model(request.getModel())
                .availability(request.isAvailability())
                .brand(request.getBrand())
                .pricePerDay(request.getPricePerDay())
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .build();
    }
    public CarEntity convert(@NotNull UpdateCarRequest request){
        return CarEntity.builder()
                .id(request.getId())
                .model(request.getModel())
                .availability(request.isAvailability())
                .brand(request.getBrand())
                .pricePerDay(request.getPricePerDay())
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .build();
    }
    public Car convert(@NotNull CarEntity car){
        return Car.builder()
                .id(car.getId())
                .model(car.getModel())
                .availability(car.isAvailability())
                .brand(car.getBrand())
                .pricePerDay(car.getPricePerDay())
                .licensePlate(car.getLicensePlate())
                .color(car.getColor())
                .build();
    }
    public CarEntity convert(@NotNull Car car){
        return CarEntity.builder()
                .id(car.getId())
                .model(car.getModel())
                .availability(car.isAvailability())
                .brand(car.getBrand())
                .pricePerDay(car.getPricePerDay())
                .licensePlate(car.getLicensePlate())
                .color(car.getColor())
                .build();
    }

}
