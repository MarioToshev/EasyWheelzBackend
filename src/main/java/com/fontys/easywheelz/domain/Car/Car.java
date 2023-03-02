package com.fontys.easywheelz.domain.Car;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String licensePlate;
    private int mileage;
    private double rentalRate;
    private boolean availability;
}
