package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        // create a mock response for the save method
        RoleEntity savedRole = RoleEntity.builder().id(1L).roleName(request.getRoleName()).build();
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(savedRole);

        // call the createRole method with the test role request
        CreateRoleResponse createdRole = createRoleUseCase.createRole(request);


        // verify that the createRole method returned the mock response
        assertEquals(1L, createdRole.getId());
    }

    @Test
    void testCreateRole_NullRequest() {
        // call the createRole method with a null request
        assertThrows(RuntimeException.class, () -> createRoleUseCase.createRole(null));
    }
}