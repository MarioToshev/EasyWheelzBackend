package com.example.easywheelz.domain.reservation;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
@Data
@Builder
public class CreateReservationRequest {
    @NonNull
    private LocalDate pickUpDate;
    @NonNull
    private LocalDate returnDate;
    @NonNull
    private double rentalRate;
    @NonNull
    private double totalCost;
    private User customer;
    @NonNull
    private Car car;
}
