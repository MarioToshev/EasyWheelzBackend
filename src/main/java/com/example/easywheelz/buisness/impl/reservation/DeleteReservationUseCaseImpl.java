package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.Errors.InvalidReservationError;
import com.example.easywheelz.buisness.interfaces.reservation.DeleteReservationUseCase;
import com.example.easywheelz.persistance.ReservationRepository;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReservationUseCaseImpl implements DeleteReservationUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public void deleteReservation(long resId) {
        if (reservationRepository.existsById(resId)){
            reservationRepository.deleteById(resId);
        }
        else
        {
            throw  new InvalidReservationError("Reservation not found");
        }
    }
}
