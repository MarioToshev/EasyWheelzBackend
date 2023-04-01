package com.example.easywheelz.buisness.reservationInterf;

import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;

public interface CreateReservationUseCase {
    CreateReservationResponse createReservation(CreateReservationRequest request);
}
