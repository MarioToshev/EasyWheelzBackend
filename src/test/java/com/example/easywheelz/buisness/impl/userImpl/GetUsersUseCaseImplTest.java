package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.userInterf.GetUsersUseCase;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest
@AllArgsConstructor
class GetUsersUseCaseImplTest {

    @Autowired
    private GetUsersUseCase getUsersUseCase;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Test
     void testGetUser() {
        RoleEntity role = RoleEntity.builder()
                .roleName("TestRole")
                .build();
        roleRepository.save(role);

        UserEntity request = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(role)
                .build();
        UserEntity user = userRepository.save(request);

        assertEquals(user,userConverter.convert(getUsersUseCase.getUser(request.getId())));

    }

    @Test
     void testGetAllUsers() {

        RoleEntity role = RoleEntity.builder()
                .roleName("TestRole")
                .build();
        roleRepository.save(role);

        UserEntity request = UserEntity.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone(1234567890)
                .driverLicense(12321312)
                .role(role)
                .build();
        UserEntity request1 = UserEntity.builder()
                .firstName("dd")
                .lastName("dd")
                .email("johnDon@example.com")
                .phone(2222222)
                .driverLicense(324551231)
                .role(role)
                .build();


        List<UserEntity> users = List.of(userRepository.save(request), userRepository.save(request1));
        assertEquals(users,getUsersUseCase.getAllUsers().stream().map(user -> userConverter.convert(user)).toList());
    }
    @Test
    void testGetNonExistantUser() {
        assertNull(getUsersUseCase.getUser(1));
    }
    @Test
    void testGetAllUsersNoUsers() {
        assertEquals(Collections.emptyList(),getUsersUseCase.getAllUsers());

    }

}