package com.example.easywheelz.buisness.roleInterf;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;

public interface DeleteRoleUseCase {
    void deleteRole(long stringId);
}
