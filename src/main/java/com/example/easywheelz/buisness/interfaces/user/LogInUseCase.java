package com.example.easywheelz.buisness.interfaces.user;

import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.domain.user.LogInResponse;

public interface LogInUseCase {
    LogInResponse login(LogInRequest loginRequest);
}
