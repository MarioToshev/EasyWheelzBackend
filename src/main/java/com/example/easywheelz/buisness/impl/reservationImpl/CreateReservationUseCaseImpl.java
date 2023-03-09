package com.example.easywheelz.buisness.impl.reservationImpl;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.reservationInterf.CreateReservationUseCase;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.persistance.impl.FakeReservationRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
    private final FakeReservationRepositoryImpl reservationRepository;
    private final ReservationConverter converter;
    @Override
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        return CreateReservationResponse.builder().id(
                reservationRepository.save(converter.convert(request)).getId()
        ).build();
    }
}
