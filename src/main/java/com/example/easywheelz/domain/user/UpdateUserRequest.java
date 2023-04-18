package com.example.easywheelz.domain.user;

import com.example.easywheelz.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UpdateUserRequest {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private long phone;
    @NotEmpty
    private String password;
    @NotEmpty
    private long driverLicense;
    @NonNull
    private Role role;
}
