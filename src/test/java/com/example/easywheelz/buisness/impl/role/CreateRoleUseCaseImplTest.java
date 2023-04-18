package com.example.easywheelz.buisness.impl.role;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.impl.role.CreateRoleUseCaseImpl;
import com.example.easywheelz.customExeptions.InvalidRoleException;
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
import static org.mockito.Mockito.verify;
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

        CreateRoleRequest request = CreateRoleRequest.builder()
                .roleName("TestRole")
                .build();

        RoleEntity roleToSave = RoleEntity.builder().roleName("TestRole").id(1L).build();


        when(roleConverter.convert(request)).thenReturn(roleToSave);
        when(roleRepository.existsRoleByRoleName(request.getRoleName())).thenReturn(false);
        when(roleRepository.save(roleToSave)).thenReturn(RoleEntity.builder().id(1L).build());

        CreateRoleResponse createdRole = createRoleUseCase.createRole(request);


        assertEquals(1L, createdRole.getId());
        verify(roleRepository).existsRoleByRoleName(request.getRoleName());
        verify(roleRepository).save(roleConverter.convert(request));
    }
    @Test
    void testCreateRoleExisting() {

        CreateRoleRequest request = CreateRoleRequest.builder()
                .roleName("TestRole")
                .build();


        when(roleRepository.existsRoleByRoleName(request.getRoleName())).thenReturn(true);

        assertThrows(InvalidRoleException.class, () -> createRoleUseCase.createRole(request));
        verify(roleRepository).existsRoleByRoleName("TestRole");
    }

    @Test
    void testCreateRole_NullRequest() {
        assertThrows(InvalidRoleException.class, () -> createRoleUseCase.createRole(null));
    }
}