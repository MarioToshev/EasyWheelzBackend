package com.example.easywheelz.buisness.interfaces.user;

import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);

}
