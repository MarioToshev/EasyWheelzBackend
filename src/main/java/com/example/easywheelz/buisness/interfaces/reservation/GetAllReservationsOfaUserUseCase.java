package com.example.easywheelz.buisness.interfaces.reservation;

import com.example.easywheelz.domain.reservation.Reservation;

import java.util.List;

public interface GetAllReservationsOfaUserUseCase {
    List<Reservation> getAllReservationsOfAUser(Long userId);
}
