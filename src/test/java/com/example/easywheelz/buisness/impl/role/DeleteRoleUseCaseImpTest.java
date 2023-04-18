package com.example.easywheelz.buisness.impl.role;

import com.example.easywheelz.customExeptions.InvalidRoleException;
import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class DeleteRoleUseCaseImpTest {



    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RoleConverter roleConverter;
    @InjectMocks
    private DeleteRoleUseCaseImp deleteRoleUseCaseImp;


    @Test
       void DeleteRoleTest(){

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);


        when(roleRepository.existsById(roleEntity.getId())).thenReturn(true);


        Exception exception = assertThrows(InvalidRoleException.class, () -> {
            deleteRoleUseCaseImp.deleteRole(roleEntity.getId());
        });


        Assertions.assertEquals("Role not found", exception.getMessage());
        verify(roleRepository).existsById(roleEntity.getId());

    }

}