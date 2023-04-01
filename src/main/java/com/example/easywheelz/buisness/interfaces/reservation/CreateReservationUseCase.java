package com.example.easywheelz.buisness.interfaces.reservation;

import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;

public interface CreateReservationUseCase {
    CreateReservationResponse createReservation(CreateReservationRequest request);
}
