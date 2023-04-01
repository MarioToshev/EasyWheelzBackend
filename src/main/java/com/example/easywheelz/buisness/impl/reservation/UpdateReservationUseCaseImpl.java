package com.example.easywheelz.buisness.impl.reservationImpl;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.reservationInterf.UpdateReservationUseCase;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateReservationUseCaseImpl implements UpdateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final ReservationConverter converter;
    @Override
    public void updateReservation(UpdateReservationRequest request) { reservationRepository.update(converter.convert(request)); }
}
