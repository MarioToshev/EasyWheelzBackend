package com.example.easywheelz.controller;

import com.example.easywheelz.Errors.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private  CreateUserUseCase createUserUseCase;
    @Mock
    private  UpdateUserUseCase updateUserUseCase;
    @Mock
    private  DeleteUserUseCase deleteUserUseCase;
    @Mock
    private  GetUsersUseCase getUsersUseCase;
    @InjectMocks
    private UserController userController;

    @Test
    public void createUserTest(){

        CreateUserRequest request = CreateUserRequest.builder()
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .build();

        CreateUserResponse response = CreateUserResponse.builder()
                .id(1L)
                .build();
        when(createUserUseCase.createUser(request)).thenReturn(response);

        ResponseEntity<CreateUserResponse> createUserResponse = userController.createUser(request);

        assertEquals(HttpStatus.CREATED, createUserResponse.getStatusCode());
        assertEquals(1L, createUserResponse.getBody().getId());
        verify(createUserUseCase).createUser(request);
    }
    @Test
    public void createUserTestWrongData(){

        CreateUserRequest request = CreateUserRequest.builder()
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .build();

        CreateUserResponse response = CreateUserResponse.builder()
                .id(1L)
                .build();
        when(createUserUseCase.createUser(request)).thenThrow(new IncorrectUserCredentialsError(""));

        assertThrows(IncorrectUserCredentialsError.class, () -> userController.createUser(request));
        verify(createUserUseCase).createUser(request);
    }
//    @Test
//    void updateUserTest() {
//        UpdateUserRequest request = UpdateUserRequest.builder()
//                .email("m@m.m")
//                .firstName("Mario")
//                .lastName("Toshev")
//                .driverLicense(29494380492L)
//                .phone(02435452423L)
//                .build();
//
//        doNothing().when(updateUserUseCase).updateUser(request);
//        ResponseEntity<Void> update = userController.updateUser(request);
//
//        assertEquals(HttpStatus.NO_CONTENT, update.getStatusCode());
//        verify(updateUserUseCase).updateUser(request);
//    }
//    @Test
//    void updateUserWrongCredentialsTest() {
//        UpdateUserRequest request = UpdateUserRequest.builder()
//                .build();
//        doThrow(new IncorrectUserCredentialsError("")).when(updateUserUseCase).updateUser(request);
//
//        assertThrows(IncorrectUserCredentialsError.class, () -> userController.updateUser(request));
//        verify(updateUserUseCase).updateUser(request);
//    }

}