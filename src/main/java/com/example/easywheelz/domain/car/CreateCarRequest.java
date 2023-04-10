package com.example.easywheelz.domain.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;


@Data
@Builder
public class CreateCarRequest {
    @NotEmpty
    private  String licensePlate;
    @NotEmpty
    private String model;
    @NotEmpty
    private String brand;
    @NotBlank
    private double pricePerDay;
    @NotEmpty
    private String color;
    private boolean availability;
}
