package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.ReservationEntity;
import com.example.easywheelz.persistance.entities.UserEntity;

import java.util.List;

public interface ReservationRepository {

    boolean existsById(long resId);
    ReservationEntity findById(long resId);
    ReservationEntity save(ReservationEntity res);
    void delete(long resId);
    List<ReservationEntity> findAll();
    void update(ReservationEntity res);
}
