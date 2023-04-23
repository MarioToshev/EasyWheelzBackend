package com.example.easywheelz.domain.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LogInRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
