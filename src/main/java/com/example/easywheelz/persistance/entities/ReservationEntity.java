package com.example.easywheelz.persistance.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ReservationEntity {
    private Long id;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private double rentalRate;
    private double totalCost;
    private UserEntity customer;
    private CarEntity car;
}
