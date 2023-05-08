package com.example.easywheelz.controller;

import com.example.easywheelz.customExeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
import com.example.easywheelz.domain.AccessToken;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Mock
    private  CreateUserUseCase createUserUseCase;
    @Mock
    private AccessToken requestAccessToken;
    @Mock
    private  UpdateUserUseCase updateUserUseCase;
    @Mock
    private  DeleteUserUseCase deleteUserUseCase;
    @Mock
    private  GetUsersUseCase getUsersUseCase;
    @InjectMocks
    private UserController userController;


    @Test
     void createUserTest(){

        CreateUserRequest request = CreateUserRequest.builder()
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .role(Role.builder().roleName("Admin").build())
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
     void createUserTestWrongData(){

        CreateUserRequest request = CreateUserRequest.builder()
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(02435452423L)
                .password("1234")
                .role(Role.builder().roleName("Admin").build())
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
    void GetUserSameTestAdmin() {

        when(requestAccessToken.hasRole("ROLE_ADMIN")).thenReturn(true);
      //  when(requestAccessToken.getUserId()).thenReturn(1L);

        User user = User.builder()
                .id(1L)
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(2435452423L)
                .password("1234")
                .role(Role.builder().roleName("Admin").build())
                .build();



        when(getUsersUseCase.getUser(user.getId())).thenReturn(user);

        ResponseEntity<User> get = userController.getUser(1L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        verify(getUsersUseCase).getUser(user.getId());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ROLE_ADMIN"})
    void GetOtherUserTestAdmin() {

        when(requestAccessToken.hasRole("ROLE_ADMIN")).thenReturn(true);

        User user = User.builder()
                .id(2L)
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(2435452423L)
                .password("1234")
                .role(Role.builder().roleName("Admin").build())
                .build();



        when(getUsersUseCase.getUser(user.getId())).thenReturn(user);

        ResponseEntity<User> get = userController.getUser(2L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        verify(getUsersUseCase).getUser(user.getId());
    }
    @Test
    void GetSameUserTestCustomer() {

        when(requestAccessToken.hasRole("ROLE_ADMIN")).thenReturn(false);
        when(requestAccessToken.getUserId()).thenReturn(1L);

        User user = User.builder()
                .id(1L)
                .email("m@m.m")
                .firstName("Mario")
                .lastName("Toshev")
                .driverLicense(29494380492L)
                .phone(2435452423L)
                .password("1234")
                .role(Role.builder().roleName("Customer").build())
                .build();



        when(getUsersUseCase.getUser(user.getId())).thenReturn(user);

        ResponseEntity<User> get = userController.getUser(1L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        verify(getUsersUseCase).getUser(user.getId());
    }
    @Test
    void GetOtherUserTestCustomer() {

        when(requestAccessToken.hasRole("ROLE_ADMIN")).thenReturn(false);
        when(requestAccessToken.getUserId()).thenReturn(2L);


        ResponseEntity<User> get = userController.getUser(1L);


        assertEquals(HttpStatus.FORBIDDEN, get.getStatusCode());
    }
    @Test
    void GetUserTestNotExistingUser() {

        when(requestAccessToken.hasRole("ROLE_ADMIN")).thenReturn(true);

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
                .phone(2435452423L)
                .password("1234")
                .role(new Role())
                .build();
        User user1 = User.builder()
                .id(2L)
                .email("d@d.d")
                .firstName("Dario")
                .lastName("Doshev")
                .driverLicense(12432123)
                .phone(2123223L)
                .password("2345")
                .role(new Role())
                .build();

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        when(getUsersUseCase.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> getAll = userController.getAllUsers();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertEquals(users.get(0).getId(), getAll.getBody().get(0).getId());
        assertEquals(users.get(1).getId(), getAll.getBody().get(1).getId());
        verify(getUsersUseCase).getAllUsers();
    }
    @Test
    void GetAllUsersTesEmpty() {
        List<User> users = new ArrayList<>();

        when(getUsersUseCase.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> getAll = userController.getAllUsers();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
       assertEquals(0,getAll.getBody().size());
        verify(getUsersUseCase).getAllUsers();
    }
}