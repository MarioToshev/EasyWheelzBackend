package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
     @NotNull
    private long phone;
    @NotNull
    private String password;
    @NotNull
    private long driverLicense;
    private Role role;
}
