package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationConverter {

    private CarConverter carConverter;
    private  UserConverter userConverter;
    private UserRepository userRepo;

    public ReservationEntity convert(CreateReservationRequest request) {
        return ReservationEntity.builder()
                .customer(userRepo.findByEmail(request.getCustomerEmail()))
                .car(carConverter.convert(request.getCar()))
                .pickUpDate(request.getPickUpDate())
                .returnDate(request.getReturnDate())
                .rentalRate(request.getRentalRate())
                .totalCost(request.getTotalCost())
                .build();
    }
    public Reservation convert(ReservationEntity request) {
        return Reservation.builder()
                .customer(userConverter.convert(request.getCustomer()))
                .car(carConverter.convert(request.getCar()))
                .pickUpDate(request.getPickUpDate())
                .returnDate(request.getReturnDate())
                .rentalRate(request.getRentalRate())
                .totalCost(request.getTotalCost())
                .build();
    }

    public ReservationEntity convert(UpdateReservationRequest request) {
        return ReservationEntity.builder()
                .customer(userConverter.convert(request.getCustomer()))
                .car(carConverter.convert(request.getCar()))
                .pickUpDate(request.getPickUpDate())
                .returnDate(request.getReturnDate())
                .rentalRate(request.getRentalRate())
                .totalCost(request.getTotalCost())
                .build();
    }
}

