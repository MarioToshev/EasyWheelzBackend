package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String password;
    private long driverLicense;
    private Role role;
}
