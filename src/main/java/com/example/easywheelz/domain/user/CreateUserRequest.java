package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private String driverLicense;
    @JsonProperty
    private Role role;
}
