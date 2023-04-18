package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
