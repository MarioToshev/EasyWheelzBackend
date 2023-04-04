package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.impl.CreateRoleUseCaseImpl;
import com.example.easywheelz.buisness.interfaces.role.CreateRoleUseCase;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleConverter roleConverter;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private CreateUserUseCaseImpl userUseCase;
    @InjectMocks
    private CreateRoleUseCaseImpl createRoleUseCase;

    @Test
    public void testCreateUser() {
        // create a test role request
        CreateRoleRequest role = CreateRoleRequest.builder()
                .roleName("TestRole")
                .build();

        CreateRoleResponse roleResp = CreateRoleResponse.builder().id(1L).build();


        when(createRoleUseCase.createRole(any(CreateRoleRequest.class))).thenReturn(roleResp);
        roleResp  = createRoleUseCase.createRole(role);


        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName(role.getRoleName()).build())
                .build();

        CreateUserResponse response = CreateUserResponse.builder().id(1L).build();

        when(userUseCase.createUser(any(CreateUserRequest.class))).thenReturn(response);
        CreateUserResponse createdUser = userUseCase.createUser(request);


        assertEquals(response, createdUser);
    }

    @Test
    public void testCreateUserWithNonexistentRole() {


        // create a test user request with a nonexistent role
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(354435)
                .role(Role.builder().roleName("NonexistentRole").build())
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userUseCase.createUser(request);
        });
        assertEquals("Role doesn't exist", exception.getMessage());

    }
}