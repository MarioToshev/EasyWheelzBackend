package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.customExeptions.InvalidReservationExeption;
import com.example.easywheelz.buisness.ReservationConverter;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateReservationUseCaseImplTest {
    @Mock
    private ReservationRepository resRepository;
    @Mock
    private ReservationConverter reservationConverter;
    @InjectMocks
    private UpdateReservationUseCaseImpl updateReservationUseCase;


    @Test
    void UpdateCarTest() {

        ReservationEntity res = ReservationEntity.builder()
                .id(1L)
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(UserEntity.builder().build())
                .car(CarEntity.builder().build())
                .build();

        res.setRentalRate(20);
        when(resRepository.existsById(res.getId())).thenReturn(true);

        updateReservationUseCase.updateReservation(UpdateReservationRequest.builder()
                .id(1L)
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build());

        when(resRepository.getReferenceById(res.getId())).thenReturn(res);
        var updatedCar = resRepository.getReferenceById(res.getId());

        assertEquals(updatedCar, res);
        verify(resRepository).existsById(res.getId());
        verify(resRepository).getReferenceById(res.getId());
    }
    @Test
    void UpdateCarTestNotExisting() {

        ReservationEntity res = ReservationEntity.builder()
                .car(CarEntity.builder().build())
                .customer(UserEntity.builder().build())
                .build();



        res.setRentalRate(20);
        when(resRepository.existsById(res.getId())).thenReturn(false);



        Exception exception = assertThrows(InvalidReservationExeption.class, () -> {
            updateReservationUseCase.updateReservation(UpdateReservationRequest.builder()
                    .car(Car.builder().build())
                    .customer(User.builder().build())
                    .build());
        });


        assertEquals("Reservation not found", exception.getMessage());
        verify(resRepository).existsById(res.getId());
    }
}