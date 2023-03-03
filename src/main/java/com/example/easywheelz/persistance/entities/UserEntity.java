package com.example.easywheelz.persistance.entities;

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
    private String driverLicense;
    private RoleEntity role;
}
