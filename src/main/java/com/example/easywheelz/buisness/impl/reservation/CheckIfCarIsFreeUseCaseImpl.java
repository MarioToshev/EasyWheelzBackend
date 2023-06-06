package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.interfaces.reservation.CheckIfCarIsFreeUseCase;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CheckIfCarIsFreeUseCaseImpl implements CheckIfCarIsFreeUseCase {
    private final ReservationRepository reservationRepository;

    @Override
    public boolean checkIfCarIsFree(long carId, LocalDate startDate, LocalDate endDate) {
        return reservationRepository.isCarUnavailableBetweenDates(carId, startDate, endDate);
    }
}
