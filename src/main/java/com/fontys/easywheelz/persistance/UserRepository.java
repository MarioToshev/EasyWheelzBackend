package com.fontys.easywheelz.persistance;

import com.fontys.easywheelz.persistance.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    boolean existsById(long userId);
    UserEntity findById(long userId);
    UserEntity save(UserEntity user);
    UserEntity delete(UserEntity user);
    List<UserEntity> findAll();

}
