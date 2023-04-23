package com.example.easywheelz.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LogInResponse {
    private  String accessToken;
}
