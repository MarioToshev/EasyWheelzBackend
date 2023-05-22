package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.reservation.CreateReservationUseCase;
import com.example.easywheelz.buisness.interfaces.reservation.DeleteReservationUseCase;
import com.example.easywheelz.buisness.interfaces.reservation.GetReservationUseCase;
import com.example.easywheelz.buisness.interfaces.reservation.UpdateReservationUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.customExeptions.InvalidReservationExeption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {
    @Mock
    private CreateReservationUseCase createResUseCase;
    @Mock
    private UpdateReservationUseCase updateResUseCase;
    @Mock
    private DeleteReservationUseCase deleteResUseCase;
    @Mock
    private GetReservationUseCase getResUseCase;
    @InjectMocks
    private ReservationController resController;

    @Test
    void createReservationTest(){


        CreateReservationRequest request = CreateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customerEmail("email")
                .car(Car.builder().build())
                .build();


        CreateReservationResponse response = CreateReservationResponse.builder()
                .id(1L)
                .build();
        when(createResUseCase.createReservation(request)).thenReturn(response);

        ResponseEntity<CreateReservationResponse> createResResponse = resController.createReservation(request);

        assertEquals(HttpStatus.CREATED, createResResponse.getStatusCode());
        assertEquals(1L, createResResponse.getBody().getId());
        verify(createResUseCase).createReservation(request);
    }
    @Test
    void createReservationTestWrongData(){
        CreateReservationRequest request = CreateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customerEmail("email")
                .car(Car.builder().build())
                .build();


        when(createResUseCase.createReservation(request)).thenThrow(new InvalidReservationExeption(""));

        assertThrows(InvalidReservationExeption.class, () -> resController.createReservation(request));
        verify(createResUseCase).createReservation(request);
    }
    @Test
    void updateReservationTest() {
        UpdateReservationRequest request = UpdateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build();

        doNothing().when(updateResUseCase).updateReservation(request);
        ResponseEntity<Void> update = resController.updateReservation(request);

        assertEquals(HttpStatus.NO_CONTENT, update.getStatusCode());
        verify(updateResUseCase).updateReservation(request);
    }
    @Test
    void updateReservationWrongCredentialsTest() {
        UpdateReservationRequest request = UpdateReservationRequest.builder()
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build();

        doThrow(new InvalidReservationExeption("")).when(updateResUseCase).updateReservation(request);

        assertThrows(InvalidReservationExeption.class, () -> resController.updateReservation(request));
        verify(updateResUseCase).updateReservation(request);
    }

    @Test
    void deleteReservationTest() {
        long resId = 1L;

        doNothing().when(deleteResUseCase).deleteReservation(resId);
        ResponseEntity<Void> delete = resController.deleteReservation(resId);

        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
        verify(deleteResUseCase).deleteReservation(resId);

    }
    void deleteReservationTestNotExisting () {
        long resID = 1L;

        doThrow(new InvalidReservationExeption("")).when(deleteResUseCase).deleteReservation(resID);

        assertThrows(InvalidReservationExeption.class, () -> resController.deleteReservation(resID));
        verify(deleteResUseCase).deleteReservation(resID);
    }


    @Test
    void GetReservationTest() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build();

        when(getResUseCase.getReservation(1L)).thenReturn(reservation);
        ResponseEntity<Reservation> get = resController.getReservation(1L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertEquals(1L, get.getBody().getId());
        verify(getResUseCase).getReservation(1L);
    }
    @Test
    void GetReservationsTestNotExistingReservation() {
        when(getResUseCase.getReservation(1L)).thenReturn(null);
        ResponseEntity<Reservation> get = resController.getReservation(1L);

        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertNull(get.getBody());
        verify(getResUseCase).getReservation(1L);
    }

    @Test
    void GetAllReservationsTest() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(23)
                .totalCost(274.94)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build();
        Reservation reservation1 = Reservation.builder()
                .id(2L)
                .pickUpDate(LocalDate.of(2023, 4, 10))
                .returnDate(LocalDate.of(2023, 4, 15))
                .rentalRate(34)
                .totalCost(333)
                .customer(User.builder().build())
                .car(Car.builder().build())
                .build();

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation1);

        when(getResUseCase.getAllReservations()).thenReturn(reservations);
        ResponseEntity<List<Reservation>> getAll = resController.getAllReservations();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertTrue(getAll.getBody().contains(reservation));
        assertTrue(getAll.getBody().contains(reservation1));
        verify(getResUseCase).getAllReservations();
    }

    @Test
    void GetAllReservationsTestEmpty() {


        List<Reservation> reservations = new ArrayList<>();

        when(getResUseCase.getAllReservations()).thenReturn(reservations);
        ResponseEntity<List<Reservation>> getAll = resController.getAllReservations();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertEquals(0,getAll.getBody().size());
        verify(getResUseCase).getAllReservations();
    }
}