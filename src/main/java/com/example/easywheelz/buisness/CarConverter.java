package com.example.easywheelz.buisness;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class CarConverter {
    public CarEntity convert(CreateCarRequest request){
        return CarEntity.builder()
                .model(request.getModel())
                .availability(request.isAvailability())
                .brand(request.getBrand())
                .pricePerDay(request.getPricePerDay())
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .build();
    }
    public CarEntity convert(UpdateCarRequest request){
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
    public Car convert(CarEntity car){
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

}
