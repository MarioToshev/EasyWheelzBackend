package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;
    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Test
     void testGetUser() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .build();


        var id = user.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User user1 = getUsersUseCase.getUser(id);

        assertEquals(userConverter.convert(user),user1);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testGetNonExistantUser() {

        assertNull(getUsersUseCase.getUser(6));
        verify(userRepository).existsById(6L);

    }
    @Test
    void testGetAllUsersNoUsers() {
        assertEquals(Collections.emptyList(),getUsersUseCase.getAllUsers());
        verify(userRepository).findAll();
    }

}