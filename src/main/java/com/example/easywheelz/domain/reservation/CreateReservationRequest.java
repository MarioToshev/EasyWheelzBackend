package com.example.easywheelz.domain.reservation;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
@Data
@Builder
public class CreateReservationRequest {
    @NotBlank
    private LocalDate pickUpDate;
    @NotBlank
    private LocalDate returnDate;
    @NotBlank
    private double rentalRate;
    @NotBlank
    private double totalCost;
    @NotNull
    private String customerEmail;
    @NotNull("Car cannot be empty")
    private Car car;
}
