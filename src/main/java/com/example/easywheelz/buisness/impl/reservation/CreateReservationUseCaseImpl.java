package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.CreateReservationUseCase;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.persistance.impl.ReservationRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
    private final ReservationRepositoryImpl reservationRepository;
    private final ReservationConverter converter;
    @Override
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        return CreateReservationResponse.builder().id(
                reservationRepository.save(converter.convert(request)).getId()
        ).build();
    }
}
