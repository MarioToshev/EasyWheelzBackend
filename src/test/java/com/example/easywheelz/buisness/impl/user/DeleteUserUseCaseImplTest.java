package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private  DeleteUserUseCaseImpl deleteUserUseCase;
    @Test
     void testDeleteUserById() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        deleteUserUseCase.deleteUser(user.getId());

        assertNull(userRepository.getReferenceById(user.getId()));
        verify(userRepository).save(user);
        verify(userRepository).findById(user.getId());
    }
    @Test
     void testDeleteUserByNonExistentId() {

        long userId = 2;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            deleteUserUseCase.deleteUser(userId);
        });

        assertEquals("You are trying to delete user that doesnt exist", exception.getMessage());
        verify(userRepository).findById(userId);

    }


}