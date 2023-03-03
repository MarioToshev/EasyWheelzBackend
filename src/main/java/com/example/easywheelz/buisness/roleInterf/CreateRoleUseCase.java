package com.example.easywheelz.buisness.roleInterf;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;

public interface CreateRoleUseCase {
    CreateRoleResponse createRole(CreateRoleRequest request);

}
