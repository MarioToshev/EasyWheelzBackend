package com.fontys.easywheelz.buisness;

import com.fontys.easywheelz.domain.User.CreateUserRequest;
import com.fontys.easywheelz.domain.User.CreateUserResponse;

public interface CreateUserUseCase {

    CreateUserResponse createUser(CreateUserRequest request);
}
