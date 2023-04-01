package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.UpdateReservationUseCase;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateReservationUseCaseImpl implements UpdateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final ReservationConverter converter;
    @Override
    public void updateReservation(UpdateReservationRequest request) { reservationRepository.save(converter.convert(request)); }
}
