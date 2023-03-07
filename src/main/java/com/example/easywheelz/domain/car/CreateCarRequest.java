package com.example.easywheelz.domain.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;


@Data
@Builder
public class CreateCarRequest {
    @NonNull
    private  String licensePlate;
    @NonNull
    private String model;
    @NonNull
    private String brand;
    @NonNull
    private double pricePerDay;
    @NonNull
    private String color;
    @NonNull
    private boolean availability;
}
