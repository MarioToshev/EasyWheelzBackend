package com.example.easywheelz.controller;

import com.example.easywheelz.Errors.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
                .role(new Role())
                .password("1234")
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
                .password("1234")
                .role(new Role())
                .build();

        CreateUserResponse response = CreateUserResponse.builder()
                .id(1L)
                .build();
        when(createUserUseCase.createUser(request)).thenThrow(new IncorrectUserCredentialsError(""));

        assertThrows(IncorrectUserCredentialsError.class, () -> userController.createUser(request));
        verify(createUserUseCase).createUser(request);
    }
    @Test
    void updateUserTest() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .password("1234")
                .role(new Role())
                .build();

        doNothing().when(updateUserUseCase).updateUser(request);
        ResponseEntity<Void> update = userController.updateUser(request);

        assertEquals(HttpStatus.NO_CONTENT, update.getStatusCode());
        verify(updateUserUseCase).updateUser(request);
    }
    @Test
    void updateUserWrongCredentialsTest() {
        UpdateUserRequest request = UpdateUserRequest.builder()  .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .password("1234")
                .role(new Role())
                .build();
        doThrow(new IncorrectUserCredentialsError("")).when(updateUserUseCase).updateUser(request);

        assertThrows(IncorrectUserCredentialsError.class, () -> userController.updateUser(request));
        verify(updateUserUseCase).updateUser(request);
    }

    @Test
    void deleteUserTest() {
        long userId = 1L;

        doNothing().when(deleteUserUseCase).deleteUser(userId);
        ResponseEntity<Void> delete = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
        verify(deleteUserUseCase).deleteUser(userId);
    }
    @Test
    void deleteUserTestNotExisting() {
        long userId = 1L;

        doThrow(new IncorrectUserCredentialsError("")).when(deleteUserUseCase).deleteUser(userId);

        assertThrows(IncorrectUserCredentialsError.class, () -> userController.deleteUser(userId));
        verify(deleteUserUseCase).deleteUser(userId);
    }


    @Test
    void GetUserTest() {
        User user = User.builder()
                .id(1L)
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .password("1234")
                .role(new Role())
                .build();

        when(getUsersUseCase.getUser(1L)).thenReturn(user);
        ResponseEntity<User> get = userController.getUser(1L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertEquals(1L, get.getBody().getId());
        verify(getUsersUseCase).getUser(1L);
    }
    @Test
    void GetUserTestNotExistingUser() {
        when(getUsersUseCase.getUser(1L)).thenReturn(null);
        ResponseEntity<User> get = userController.getUser(1L);

        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertNull(get.getBody());
        verify(getUsersUseCase).getUser(1L);
    }

    @Test
    void GetAllUsersTest() {
        User user = User.builder()
                .id(1L)
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .password("1234")
                .role(new Role())
                .build();
        User user1 = User.builder()
                .id(2L)
                .email("d@d.d")
                .firstName("Dario")
                .lastName("Doshev")
                .driverLicense(12432123)
                .phone(02123223L)
                .password("2345")
                .role(new Role())
                .build();

        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user1);

        when(getUsersUseCase.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> getAll = userController.getAllUsers();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertEquals(users.get(0).getId(), getAll.getBody().get(0).getId());
        assertEquals(users.get(1).getId(), getAll.getBody().get(1).getId());
        verify(getUsersUseCase).getAllUsers();
    }
}