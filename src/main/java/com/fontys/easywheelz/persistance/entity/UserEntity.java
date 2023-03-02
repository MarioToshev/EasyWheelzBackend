package com.fontys.easywheelz.persistance.entity;

import domain.Role.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String driverLicenseNumber;
    private Role role;
}
