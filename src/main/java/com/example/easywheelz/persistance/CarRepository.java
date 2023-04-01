package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.CarEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

}
