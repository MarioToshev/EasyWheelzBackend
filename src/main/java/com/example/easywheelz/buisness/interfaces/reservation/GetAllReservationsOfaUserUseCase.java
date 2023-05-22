package com.example.easywheelz.buisness.interfaces.reservation;

import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.entities.RoleEntity;

import java.util.List;

public interface GetAllReservationsOfaUserUseCase {
    List<Reservation> getAllReservationsOfAUser(Long userId);
}
