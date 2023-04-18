package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {

    boolean existsByEmail(@NotBlank String email);
    boolean existsByPhone(@NotNull long phone);
    UserEntity findByEmail(String email);



}
