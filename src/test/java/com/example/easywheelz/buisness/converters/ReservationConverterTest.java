package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class ReservationConverterTest {
    private ReservationConverter converter;

    public ReservationConverterTest(){
     //   converter = new ReservationConverter();
    }
    @Test
    void convertCreateReservationRequestToReservationEntity() {

        User user = User.builder()
                .role(Role.builder().roleName("role").build())
                .build();


        CreateReservationRequest reservation = CreateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                //.customer(user)
                .car(Car.builder().build())
                .build();

        ReservationEntity reservationEntity = converter.convert(reservation);

        assertEquals(reservationEntity.getPickUpDate(),reservation.getPickUpDate());
        assertEquals(reservationEntity.getReturnDate(),reservation.getReturnDate());
        assertEquals(reservationEntity.getRentalRate(),reservation.getRentalRate());
        assertEquals(reservationEntity.getTotalCost(),reservation.getTotalCost());

    }
    @Test
    void convertCreateReservationRequestToReservationEntityWrong() {

        CreateReservationRequest reservation = null;

        assertThrows(NullPointerException.class, () -> converter.convert(reservation));
    }
    @Test
    void convertReservationEntityToReservation() {

        UserEntity user = UserEntity.builder()
                .role(RoleEntity.builder().roleName("role").build())
                .build();


        ReservationEntity reservationEntity  = ReservationEntity.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(user)
                .car(CarEntity.builder().build())
                .build();

        Reservation reservation = converter.convert(reservationEntity);

        assertEquals(reservationEntity.getPickUpDate(),reservation.getPickUpDate());
        assertEquals(reservationEntity.getReturnDate(),reservation.getReturnDate());
        assertEquals(reservationEntity.getRentalRate(),reservation.getRentalRate());
        assertEquals(reservationEntity.getTotalCost(),reservation.getTotalCost());

    }
    @Test
    void convertReservationEntityToReservationWrong() {

        ReservationEntity reservation = null;

        assertThrows(NullPointerException.class, () -> converter.convert(reservation));
    }

    @Test
    void convertUpdateReservationRequestToReservationEntity() {

        User user = User.builder()
                .role(Role.builder().roleName("role").build())
                .build();


        UpdateReservationRequest reservation = UpdateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(user)
                .car(Car.builder().build())
                .build();

        ReservationEntity reservationEntity = converter.convert(reservation);

        assertEquals(reservationEntity.getPickUpDate(),reservation.getPickUpDate());
        assertEquals(reservationEntity.getReturnDate(),reservation.getReturnDate());
        assertEquals(reservationEntity.getRentalRate(),reservation.getRentalRate());
        assertEquals(reservationEntity.getTotalCost(),reservation.getTotalCost());

    }
    @Test
    void convertUpdateReservationRequestToReservationEntityWrong() {

        UpdateReservationRequest reservation = null;

        assertThrows(NullPointerException.class, () -> converter.convert(reservation));
    }


}