package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class RoleConverterTest {
    private RoleConverter converter;

    public RoleConverterTest(){
        converter = new RoleConverter();
    }


    @Test
    void convertCreateRoleRequestToRoleEntity() {

        CreateRoleRequest role = CreateRoleRequest.builder()
                .roleName("TestRole")
                .build();

        RoleEntity roleEntity = converter.convert(role);

        assertEquals(roleEntity.getRoleName(), role.getRoleName());

    }
    @Test
    void convertCreateRoleRequestToRoleEntityError() {

        CreateRoleRequest role = null;

        assertThrows(NullPointerException.class, () -> converter.convert(role));
    }
    @Test
    void convertRoleToRoleEntity() {

        Role role = Role.builder()
                .roleName("TestRole")
                .build();

        RoleEntity roleEntity = converter.convert(role);

        assertEquals(roleEntity.getRoleName(), role.getRoleName());

    }
    @Test
    void convertRoleToRoleEntityWrong() {

        Role role = null;

        assertThrows(NullPointerException.class, () -> converter.convert(role));
    }
    @Test
    void convertRoleEntityToRole() {

        RoleEntity roleEntity  = RoleEntity.builder()
                .roleName("TestRole")
                .build();

        Role role = converter.convert(roleEntity);

        assertEquals(roleEntity.getRoleName(), role.getRoleName());

    }
    @Test
    void convertRoleEntityToRoleWrong() {

        RoleEntity role = null;

        assertThrows(NullPointerException.class, () -> converter.convert(role));
    }

}