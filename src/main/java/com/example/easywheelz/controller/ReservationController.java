package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.reservation.*;
import com.example.easywheelz.configuration.security.isauthenticated.IsAuthenticated;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.reservation.UpdateReservationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReservationController {
    private  final CreateReservationUseCase createReservation;
    private  final DeleteReservationUseCase deleteReservation;
    private  final UpdateReservationUseCase updateReservation;

    private  final GetReservationUseCase getReservation;
    private  final GetAllReservationsOfaUserUseCase getAllReservationsOfaUserUseCase;

    @PostMapping("")
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
        CreateReservationResponse response = createReservation.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(getReservation.getAllReservations());
    }
    @PostMapping("/{userId}")
    public ResponseEntity<List<Reservation>> getAllUserReservations(@PathVariable Long userId) {
        return ResponseEntity.ok(getAllReservationsOfaUserUseCase.getAllReservationsOfAUser(userId));
    }
    @GetMapping("/{resId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable long resId) {
        return ResponseEntity.ok(getReservation.getReservation(resId));
    }
    @PutMapping("")
    public ResponseEntity<Void> updateReservation(@RequestBody UpdateReservationRequest request) {
        updateReservation.updateReservation(request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{resId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long resId) {
        deleteReservation.deleteReservation(resId);
        return ResponseEntity.noContent().build();
    }
}
