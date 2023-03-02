package com.fontys.easywheelz.domain;

import domain.Car.Car;
import domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private double rentalRate;
    private double totalCost;

    private User customer;
    private Car car;
}
