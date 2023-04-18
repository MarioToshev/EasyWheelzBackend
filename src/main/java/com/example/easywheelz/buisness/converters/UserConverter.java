package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.springframework.stereotype.Service;
@Service
public class UserConverter {
    public UserConverter() {
    }
    public UserEntity convert(CreateUserRequest request ){
        return UserEntity.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .driverLicense(request.getDriverLicense())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .role(
                        RoleEntity.builder().roleName(request.getRole().getRoleName())
                                .id(request.getRole().getId()).build()
                )
                .build();
    }
    public UserEntity convert(UpdateUserRequest request){
        return UserEntity.builder()
                .id(request.getId())
                .phone(request.getPhone())
                .email(request.getEmail())
                .driverLicense(request.getDriverLicense())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .role(
                        RoleEntity.builder().roleName(request.getRole().getRoleName())
                                .id(request.getRole().getId()).build()
                )
                .build();
    }
    public User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .driverLicense(user.getDriverLicense())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(
                        Role.builder().roleName(user.getRole().getRoleName())
                                .id(user.getRole().getId()).build()
                )
                .build();
    }
    public UserEntity convert(User user){
        return UserEntity.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .email(user.getEmail())
                .driverLicense(user.getDriverLicense())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(
                        RoleEntity.builder().roleName(user.getRole().getRoleName())
                                .id(user.getRole().getId()).build()
                )
                .build();
    }

}
