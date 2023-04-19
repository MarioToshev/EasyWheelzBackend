package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleConverter {
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
    public  Role convert(RoleEntity role) {
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
