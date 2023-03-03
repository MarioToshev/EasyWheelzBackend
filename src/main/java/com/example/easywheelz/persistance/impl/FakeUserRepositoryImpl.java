package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private final List<UserEntity> users;
    private static long NEXT_ID = 1;

    public FakeUserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public boolean existsById(long userId) {
        return users.stream()
                .anyMatch(userEntity -> userEntity.getId().equals(userId));
    }

    @Override
    public UserEntity findById(long userId) {
        return users.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst().get();

    }

    @Override
    public UserEntity save(UserEntity user) {
        user.setId(NEXT_ID);
        NEXT_ID++;
        users.add(user);
        return user;
    }

    @Override
    public UserEntity delete(UserEntity user) {
        users.remove(users.stream().filter(userEntity -> userEntity.getId().equals(user.getId())).findFirst());
        return  user;
    }

    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(users);
    }

    @Override
    public void update(UserEntity user) {
        users.stream()
                .filter(userEntity -> userEntity.getId().equals(user.getId()))
                .findFirst()
                .ifPresent(userEntity -> {
                    userEntity.setEmail(user.getEmail());
                    userEntity.setPhone(user.getPhone());
                    userEntity.setRole(user.getRole());
                    userEntity.setDriverLicense(user.getDriverLicense());
                });
    }
}
