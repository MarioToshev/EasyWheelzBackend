package com.fontys.easywheelz.buisness;

import com.fontys.easywheelz.domain.User.CreateUserRequest;
import org.springframework.stereotype.Service;
import com.fontys.easywheelz.persistance.entity.UserEntity;

@Service
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
