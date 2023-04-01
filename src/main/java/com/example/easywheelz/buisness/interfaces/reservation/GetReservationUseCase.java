package com.example.easywheelz.buisness.reservationInterf;

import com.example.easywheelz.domain.reservation.Reservation;

import java.util.List;

public interface GetReservationUseCase {
    public Reservation getReservation(long id);
    public List<Reservation> getAllReservations();
}
