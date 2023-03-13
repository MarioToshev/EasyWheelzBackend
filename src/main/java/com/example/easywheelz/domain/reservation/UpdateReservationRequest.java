package com.example.easywheelz.domain.reservation;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
@Data
@Builder
public class UpdateReservationRequest {
    private Long id;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private double rentalRate;
    private double totalCost;
    private User customer;
    @NonNull
    private Car car;
}
