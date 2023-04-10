package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.Errors.InvalidCarCredentials;
import com.example.easywheelz.Errors.InvalidReservationError;
import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteReservationUseCaseImplTest {

    @Mock
    private ReservationRepository resRepository;
    @Mock
    private ReservationConverter reservationConverter;
    @InjectMocks
    private DeleteReservationUseCaseImpl deleteReservationUseCase;

    @Test
    void DeleteReservationTest(){

        ReservationEntity resEnt = new ReservationEntity();
        resEnt.setId(1L);


        when(resRepository.existsById(resEnt.getId())).thenReturn(true);
        when(resRepository.getReferenceById(resEnt.getId())).thenReturn(resEnt);

        deleteReservationUseCase.deleteReservation(resEnt.getId());

        assertNotNull(resRepository.getReferenceById(resEnt.getId()));
        verify(resRepository).existsById(resEnt.getId());
        verify(resRepository).deleteById(resEnt.getId());
    }
    @Test
    void DeleteNotExistinReservationTest(){
        CarEntity carEnt = new CarEntity();
        carEnt.setId(1);

        when(resRepository.existsById(carEnt.getId())).thenReturn(false);

        Exception exception = assertThrows(InvalidReservationError.class, () -> {
            deleteReservationUseCase.deleteReservation(carEnt.getId());
        });

        assertEquals("Reservation not found" ,exception.getMessage());
        verify(resRepository).existsById(carEnt.getId());
    }

}