package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRoleUseCaseImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleConverter roleConverter;
    @InjectMocks
    private CreateRoleUseCaseImpl createRoleUseCase;

    @Test
    void testCreateRole() {
        // create a test role request
        CreateRoleRequest request = CreateRoleRequest.builder()
                .roleName("TestRole")
                .build();

        RoleEntity roleToSave = RoleEntity.builder().roleName("TestRole").id(1L).build();

        CreateRoleResponse savedRole = CreateRoleResponse.builder().id(1L).build();

        when(roleConverter.convert(request)).thenReturn(roleToSave);
        when(roleRepository.save(roleToSave)).thenReturn(RoleEntity.builder().id(1L).build());

        CreateRoleResponse createdRole = createRoleUseCase.createRole(request);


        assertEquals(1L, createdRole.getId());
    }

    @Test
    void testCreateRole_NullRequest() {
        assertThrows(RuntimeException.class, () -> createRoleUseCase.createRole(null));
    }
}