package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.interfaces.reservation.DeleteReservationUseCase;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReservationUseCaseImpl implements DeleteReservationUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public void deleteReservation(long userId) {
        reservationRepository.deleteById(userId);
    }
}