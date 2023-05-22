package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.converters.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.CheckIfCarIsFreeUseCase;
import com.example.easywheelz.buisness.interfaces.reservation.CreateReservationUseCase;
import com.example.easywheelz.custom.exeptions.InvalidReservationExeption;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreateReservationUseCaseImpl implements CreateReservationUseCase {
        private final CheckIfCarIsFreeUseCase checkIfCarIsFreeUseCase;
    private final ReservationRepository reservationRepository;
    private final ReservationConverter converter;
    @Override
    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest request) {
        List<ReservationEntity> overlappingReservations = reservationRepository.findOverlappingReservations(request.getCar().getId(),
                request.getPickUpDate(), request.getReturnDate());
        if (overlappingReservations.isEmpty()) {

            if (checkIfCarIsFreeUseCase.checkIfCarIsFree(request.getCar().getId(), request.getPickUpDate(), request.getReturnDate())) {
                return CreateReservationResponse.builder().id(
                        reservationRepository.save(converter.convert(request)).getId()
                ).build();
            }
        }
          throw new  InvalidReservationExeption("This car is not available on the chosen dates");
    }
}
