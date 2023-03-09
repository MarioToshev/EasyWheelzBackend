package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.persistance.ReservationRepository;
import com.example.easywheelz.persistance.entities.ReservationEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
@AllArgsConstructor
public class FakeReservationRepositoryImpl implements ReservationRepository {
    private final List<ReservationEntity> reservations;
    private static long NEXT_ID = 1;

    public FakeReservationRepositoryImpl() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public boolean existsById(long resId) {
        return reservations.stream()
                .anyMatch(resEntity -> resEntity.getId().equals(resId));
    }

    @Override
    public ReservationEntity findById(long resId) {
        return reservations.stream()
                .filter(userEntity -> userEntity.getId().equals(resId))
                .findFirst().get();

    }

    @Override
    public ReservationEntity save(ReservationEntity res) {
        res.setId(NEXT_ID);
        NEXT_ID++;
        reservations.add(res);
        return res;
    }

    @Override
    public void delete(long resId) {
        reservations.remove(reservations.stream().filter(reservationEntity -> reservationEntity.getId().equals(resId)).findFirst());
    }

    @Override
    public List<ReservationEntity> findAll() {
        return Collections.unmodifiableList(reservations);
    }

    @Override
    public void update(ReservationEntity res) {
        reservations.stream()
                .filter(reservationEntity -> reservationEntity.getId().equals(res.getId()))
                .findFirst()
                .ifPresent(reservationEntity -> {
                    reservationEntity.setCustomer(res.getCustomer());
                    reservationEntity.setCar(res.getCar());
                    reservationEntity.setReturnDate(res.getReturnDate());
                    reservationEntity.setRentalRate(res.getRentalRate());
                    reservationEntity.setTotalCost(res.getTotalCost());
                    reservationEntity.setPickUpDate(res.getPickUpDate());
                    reservationEntity.setId(reservationEntity.getId());
                });
    }
}
