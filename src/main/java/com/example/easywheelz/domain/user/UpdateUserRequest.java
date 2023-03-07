package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@Builder
public class UpdateUserRequest {
    @NonNull
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private int phone;
    @NonNull
    private int driverLicense;
    @NonNull
    private Role role;
}
