package com.example.easywheelz.domain.car;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    @NotBlank
    private long id;
    @NotBlank
    private  String licensePlate;
    @NotBlank
    private String model;
    @NotBlank
    private String brand;
    @NotBlank
    private double pricePerDay;
    @NotBlank
    private String color;
    private String photoUrl;
    @NotBlank
    private boolean availability;

}
