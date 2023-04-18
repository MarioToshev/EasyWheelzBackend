package com.example.easywheelz.domain.reservation;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class Reservation {
    @NotBlank
    private Long id;
    @NotBlank
    private LocalDate pickUpDate;
    @NotBlank
    private LocalDate returnDate;
    @NotBlank
    private double rentalRate;
    @NotBlank
    private double totalCost;
    @NotBlank
    private User customer;
    @NotNull
    private Car car;
}
