package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

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
     @NotBlank
    private long phone;
    @NotBlank
    private long driverLicense;
    @NonNull
    private Role role;
}
