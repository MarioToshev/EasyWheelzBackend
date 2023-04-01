package com.example.easywheelz.domain.car;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;


@Data
@Builder
public class CreateCarRequest {
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
    @NotBlank
    private boolean availability;
}
