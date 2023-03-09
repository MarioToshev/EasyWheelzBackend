package com.example.easywheelz.buisness.impl.reservationImpl;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.reservationInterf.GetReservationUseCase;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.impl.FakeReservationRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GetReservationUseCaseImpl implements GetReservationUseCase
{
    private final ReservationRepository reservationRepository ;
    private final ReservationConverter converter;
    @Override
    public Reservation getReservation(long id){ return converter.convert(reservationRepository.findById(id));}
    @Override
    public List<Reservation> getAllReservations() { return reservationRepository.findAll().stream().map(converter::convert).toList();}
}
