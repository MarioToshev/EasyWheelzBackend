package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.GetReservationUseCase;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GetReservationUseCaseImpl implements GetReservationUseCase
{
    private final ReservationRepository reservationRepository ;
    private final ReservationConverter converter;
    @Override
    public Reservation getReservation(long id){

        if(reservationRepository.existsById(id)){
            return converter.convert(reservationRepository.getReferenceById(id));
        }
        throw new RuntimeException("Reservation not found");
    }
    @Override
    public List<Reservation> getAllReservations() { return reservationRepository.findAll().stream().map(converter::convert).toList();}
}