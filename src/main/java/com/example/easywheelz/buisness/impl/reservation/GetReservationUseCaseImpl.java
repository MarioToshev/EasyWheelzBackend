package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.converters.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.GetReservationUseCase;
import com.example.easywheelz.customExeptions.InvalidReservationExeption;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
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
    public Reservation getReservation(long id){

        if(reservationRepository.existsById(id)){
            return converter.convert(reservationRepository.getReferenceById(id));
        }
        throw new InvalidReservationExeption("Reservation not found");
    }
    @Override
    public List<Reservation> getAllReservations() { return reservationRepository.findAll().stream().map(converter::convert).toList();}
}
