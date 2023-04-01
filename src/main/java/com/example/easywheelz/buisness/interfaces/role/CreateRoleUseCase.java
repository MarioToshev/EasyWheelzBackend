package com.example.easywheelz.buisness.interfaces.role;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;

public interface CreateRoleUseCase {
    CreateRoleResponse createRole(CreateRoleRequest request);

}
