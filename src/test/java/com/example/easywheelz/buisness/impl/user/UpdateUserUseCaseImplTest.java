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
        long roleId = 1;

        UserEntity user = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .role(RoleEntity.builder().id(roleId).build())
                .driverLicense(12321312)
                .build();

        user.setEmail("john@gmail.com");

        when(roleRepository.existsById(roleId)).thenReturn(true);

        updateUsersUseCase.updateUser(UpdateUserRequest.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .driverLicense(user.getDriverLicense())
                .role(Role.builder().id(roleId).build())
                .build()
        );
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);
        var updatedUser = userRepository.getReferenceById(user.getId());

        assertEquals(updatedUser, user);
        verify(roleRepository).existsById(roleId);

    }

    @Test
    void UpdateUserTestUnexistingRole() {
        long roleId = 1;

        UserEntity user = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .role(RoleEntity.builder().id(roleId).build())
                .driverLicense(12321312)
                .build();

        user.setEmail("john@gmail.com");

        when(roleRepository.existsById(roleId)).thenReturn(false);


        when(userRepository.getReferenceById(user.getId())).thenReturn(user);
        var updatedUser = userRepository.getReferenceById(user.getId());

        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .driverLicense(user.getDriverLicense())
                .role(Role.builder().id(roleId).build())
                .build();

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            updateUsersUseCase.updateUser(request);
        });

        assertEquals("Role doesn't exist", exception.getMessage());
        assertEquals(updatedUser, user);
        verify(roleRepository).existsById(roleId);
    }
}