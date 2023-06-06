package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private UpdateUserUseCaseImpl updateUsersUseCase;

    @Test
    void UpdateUserTest() {
        String roleName = "role";

        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .role(RoleEntity.builder().id(1L).roleName(roleName).build())
                .driverLicense(12321312)
                .build();

        user.setEmail("john@gmail.com");

        when(roleRepository.findByRoleName(roleName)).thenReturn(RoleEntity.builder().id(1L).roleName(roleName).build());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);


        var request =
                UpdateUserRequest.builder()
                        .id(user.getId())
                        .phone(user.getPhone())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .driverLicense(user.getDriverLicense())
                        .role("role")
                        .build();
        when(userConverter.convert(request)).thenReturn(user);


        updateUsersUseCase.updateUser(request);

        when(userRepository.getReferenceById(user.getId())).thenReturn(user);
        var updatedUser = userRepository.getReferenceById(user.getId());

        assertEquals(updatedUser, user);
        verify(roleRepository).findByRoleName(roleName);
        verify(userRepository).findByEmail(user.getEmail());


    }

    @Test
    void UpdateUserTestUnexistingRole() {

        String roleName = "role";

        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .role(RoleEntity.builder().id(1L).roleName(roleName).build())
                .driverLicense(12321312)
                .build();

        user.setEmail("john@gmail.com");

        when(roleRepository.findByRoleName(roleName)).thenReturn(null);

        var request =
                UpdateUserRequest.builder()
                        .id(user.getId())
                        .phone(user.getPhone())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .driverLicense(user.getDriverLicense())
                        .role("role")
                        .build();

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            updateUsersUseCase.updateUser(request);
        });

        assertEquals("Role doesn't exist", exception.getMessage());
        verify(roleRepository).findByRoleName(roleName);
    }
}