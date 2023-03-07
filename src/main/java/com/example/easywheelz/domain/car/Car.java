package com.example.easywheelz.domain.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Car {

    private long id;
    private  String licensePlate;
    private String model;
    private String brand;
    private double pricePerDay;
    private String color;
    private boolean availability;

}
