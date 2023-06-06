package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.converters.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.GetAllReservationsOfaUserUseCase;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetAllReservatiosnOfAUserUseCaseImpl implements GetAllReservationsOfaUserUseCase {

    private ReservationRepository repo;
    private ReservationConverter converter;

    @Override
    public List<Reservation> getAllReservationsOfAUser(Long userId) {
        return repo.findAllByCustomerId(userId).stream().map(converter::convert).toList();
    }
}
