package com.example.easywheelz.buisness;

import com.example.easywheelz.buisness.roleInterf.CreateRoleUseCase;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public final class RoleConverter {
    private RoleConverter() {
    }
    public RoleEntity convert(CreateRoleRequest request) {
        return RoleEntity.builder()
                .roleName(request.getRoleName())
                .build();
    }
    public RoleEntity convert(Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
