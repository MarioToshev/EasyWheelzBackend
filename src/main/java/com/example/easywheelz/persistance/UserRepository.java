package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    boolean existsById(long userId);
    UserEntity findById(long userId);
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
    List<UserEntity> findAll();
    void update(UserEntity user);

}
