package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.roleInterf.CreateRoleUseCase;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {
    private final FakeRoleRepositoryImpl roleRepository;

    private final RoleConverter converter;

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        return CreateRoleResponse.builder().id(
                roleRepository.save(converter.convert(request)).getId()
        ).build();
    }
}
