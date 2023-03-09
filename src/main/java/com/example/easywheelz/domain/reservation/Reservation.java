package com.example.easywheelz.domain.reservation;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
public class Reservation {

    private Long id;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private double rentalRate;
    private double totalCost;
    private User customer;
    private Car car;
}
