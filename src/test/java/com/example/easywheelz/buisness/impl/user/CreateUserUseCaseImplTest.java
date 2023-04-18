package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.customExeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.impl.role.CreateRoleUseCaseImpl;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    void testCreateUser() {
        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setRoleName("TestRole");

        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName(role.getRoleName()).build())
                .build();

        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.existsByPhone(request.getPhone())).thenReturn(false);
        when(roleRepository.findByRoleName("Customer")).thenReturn(role);
        when(userRepository.save(userConverter.convert(request))).thenReturn(user);

        CreateUserResponse response = userUseCase.createUser(request);

        assertEquals(1, response.getId());
        verify(userRepository).existsByEmail(anyString());
        verify(userRepository).existsByPhone(anyLong());
        verify(roleRepository).findByRoleName(anyString());
        verify(userRepository).save(any());

    }



    @Test
    void testCreateUserWithExistingEmail() {


        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setRoleName("TestRole");

        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName(role.getRoleName()).build())
                .build();

        UserEntity user = new UserEntity();
        user.setId(1L);


        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            userUseCase.createUser(request);
        });

        assertEquals("This email is already in use.", exception.getMessage());

        assertEquals("This email is already in use.",exception.getMessage() );
        verify(userRepository).existsByEmail(any());
        //the other methods are after this check so no need to veryfy them
    }

    @Test
    void testCreateUserWithExistingPhoneNumber() {


        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setRoleName("TestRole");

        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName(role.getRoleName()).build())
                .build();

        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);

        when(userRepository.existsByPhone(request.getPhone())).thenReturn(true);

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            userUseCase.createUser(request);
        });

        assertEquals("This phone number is already in use.", exception.getMessage());

        assertEquals("This phone number is already in use.",exception.getMessage() );
        verify(userRepository).existsByPhone(anyLong());
        verify(userRepository).existsByEmail(any());
        //the other methods are after this check so no need to verify them
    }
}