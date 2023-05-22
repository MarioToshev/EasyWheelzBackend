package com.example.easywheelz.buisness.impl.reservation;

import com.example.easywheelz.buisness.converters.ReservationConverter;
import com.example.easywheelz.buisness.interfaces.reservation.CheckIfCarIsFreeUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseImplTest {

    @Mock
    private CheckIfCarIsFreeUseCase checkIfCarIsFreeUseCase;

    @Mock
    private ReservationRepository resRepository;
    @Mock
    private ReservationConverter reservationConverter;
    @InjectMocks
    private CreateReservationUseCaseImpl createReservationUseCase;

    @Test
    void CreateReservationTest(){

        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setRoleName("TestRole");

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName(role.getRoleName()).build())
                .build();

        CreateReservationRequest reservation = CreateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customerEmail("userEmail")
                .car(Car.builder().build())
                .build();
        when(checkIfCarIsFreeUseCase.checkIfCarIsFree(reservation.getCar().getId(),reservation.getPickUpDate(),reservation.getReturnDate())).thenReturn(true);

        when(resRepository.save(reservationConverter.convert(reservation))).thenReturn(
                ReservationEntity.builder()
                        .id(1L)
                        .customer(new UserEntity())
                        .car(new CarEntity())
                        .build()
        );
        var response = createReservationUseCase.createReservation(reservation);


        assertEquals(1L,response.getId());
        verify(resRepository).save(reservationConverter.convert(reservation));
    }

    @Test
    void CreateReservationTestNoUser(){


        Exception exception = assertThrows(NullPointerException.class, () -> {
                    CreateReservationRequest.builder()
                            .pickUpDate(LocalDate.of(2023, 4, 10))
                            .returnDate(LocalDate.of(2023, 4,15))
                            .rentalRate(23)
                            .totalCost(274.94)
                            .car(Car.builder().build())
                            .build();
        });

        assertEquals("customerEmail is marked non-null but is null",exception.getMessage());
    }
    @Test
    void CreateReservationTestNoCar(){


        Exception exception = assertThrows(NullPointerException.class, () -> {
            CreateReservationRequest.builder()
                    .pickUpDate(LocalDate.of(2023, 4, 10))
                    .returnDate(LocalDate.of(2023, 4,15))
                    .rentalRate(23)
                    .totalCost(274.94)
                    .customerEmail("")
                    .build();
        });

        assertEquals("car is marked non-null but is null",exception.getMessage());
    }

}