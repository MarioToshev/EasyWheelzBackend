package com.example.easywheelz.buisness;

import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.persistance.entities.UserEntity;

public class UserConverter {
    private UserConverter() {
    }
    public UserEntity convert(CreateUserRequest request){
        return UserEntity.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .driverLicenseNumber(request.getDriverLicense())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }
}
