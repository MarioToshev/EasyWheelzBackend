package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    private UserConverter converter;

    public UserConverterTest(){
        converter = new UserConverter();
    }

    @Test
    void convertCreateUserRequestToUserEntity() {
        CreateUserRequest user = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName("Role").build())
                .build();

        UserEntity userEntity = converter.convert(user);

        assertEquals(userEntity.getPhone(), user.getPhone());
        assertEquals(userEntity.getDriverLicense(), user.getDriverLicense());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getPassword(), user.getPassword());
    }
    @Test
    void convertCreateUserRequestToUserEntityWrong() {

        CreateUserRequest user = null;

        assertThrows(NullPointerException.class, () -> converter.convert(user));
    }
    @Test
    void convertUpdateUserRequestToUserEntity() {
        UpdateUserRequest user = UpdateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role("Role")
                .build();

        UserEntity userEntity = converter.convert(user);

        assertEquals(userEntity.getPhone(), user.getPhone());
        assertEquals(userEntity.getDriverLicense(), user.getDriverLicense());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getPassword(), user.getPassword());
    }
    @Test
    void convertUpdateUserRequestToUserEntityWrong() {

        UpdateUserRequest user = null;

        assertThrows(NullPointerException.class, () -> converter.convert(user));
    }
    @Test
    void convertUserEntityToUser() {
        UserEntity userEntity  = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(RoleEntity.builder().roleName("Role").build())
                .build();

        User user = converter.convert(userEntity);

        assertEquals(userEntity.getPhone(), user.getPhone());
        assertEquals(userEntity.getDriverLicense(), user.getDriverLicense());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getPassword(), user.getPassword());
    }
    @Test
    void convertUserEntityToUserWrong() {

        UserEntity user = null;

        assertThrows(NullPointerException.class, () -> converter.convert(user));
    }
    @Test
    void convertUserToUserEntity() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(Role.builder().roleName("Role").build())
                .build();

        UserEntity userEntity = converter.convert(user);

        assertEquals(userEntity.getPhone(), user.getPhone());
        assertEquals(userEntity.getDriverLicense(), user.getDriverLicense());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getFirstName(), user.getFirstName());
        assertEquals(userEntity.getLastName(), user.getLastName());
        assertEquals(userEntity.getPassword(), user.getPassword());
    }
    @Test
    void convertUserToUserEntityWrong() {

        User user = null;

        assertThrows(NullPointerException.class, () -> converter.convert(user));
    }


}