package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.Errors.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.impl.CreateRoleUseCaseImpl;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
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

import java.util.Collections;

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

        when(userRepository.existsById(user.getId())).thenReturn(true);
        deleteUserUseCase.deleteUser(user.getId());

        assertNull(userRepository.getReferenceById(user.getId()));
        verify(userRepository).deleteById(user.getId());
        verify(userRepository).existsById(user.getId());
    }
    @Test
     void testDeleteUserByNonExistentId() {

        long userId = 2;

        when(userRepository.existsById(userId)).thenReturn(false);

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            deleteUserUseCase.deleteUser(userId);
        });

        assertEquals("You are trying to delete user that doesnt exist", exception.getMessage());
        verify(userRepository).existsById(userId);

    }


}