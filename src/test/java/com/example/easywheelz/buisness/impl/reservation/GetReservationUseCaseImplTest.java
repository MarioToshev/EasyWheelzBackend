package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.customExeptions.InvalidReservationExeption;
import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReservationUseCaseImplTest {
    @Mock
    private ReservationRepository resRepository;
    @Mock
    private ReservationConverter reservationConverter;
    @InjectMocks
    private GetReservationUseCaseImpl getReservationUseCase;

    @Test
    void testGetUser() {
        ReservationEntity resEnt = new ReservationEntity();
        resEnt.setId(1L);



        when(resRepository.existsById(resEnt.getId())).thenReturn(true);
        when(resRepository.getReferenceById(resEnt.getId())).thenReturn(resEnt);

        Reservation res1 = getReservationUseCase.getReservation(resEnt.getId());

        assertEquals(reservationConverter.convert(resEnt),res1);
        verify(resRepository).existsById(resEnt.getId());
        verify(resRepository).getReferenceById(resEnt.getId());
    }

    @Test
    void testGetNonExistingCar() {

        Exception exception = assertThrows(InvalidReservationExeption.class, () -> {
            getReservationUseCase.getReservation(1L);
        });

        assertEquals("Reservation not found", exception.getMessage());
        verify(resRepository).existsById(1L);
    }

    @Test
    void testGetAllCarsNoCars() {
        assertEquals(Collections.emptyList(),getReservationUseCase.getAllReservations());
        verify(resRepository).findAll();
    }
}