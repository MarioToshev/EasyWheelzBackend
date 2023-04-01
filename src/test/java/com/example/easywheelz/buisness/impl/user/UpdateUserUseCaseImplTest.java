package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.userInterf.UpdateUserUseCase;
import com.example.easywheelz.domain.role.Role;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AllArgsConstructor
class UpdateUserUseCaseImplTest {
    @Autowired
    private UpdateUserUseCase updateUsersUseCase;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private UserConverter userConverter;

    @Test
    void UpdateUserTest(){
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
        user.setEmail("john@gmail.com");



        updateUsersUseCase.updateUser(UpdateUserRequest.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .driverLicense(user.getDriverLicense())
                .role(roleConverter.convert(role))
                .build()
        );
        assertEquals(userRepository.findById(user.getId()),user);
    }
//    @Test
//    void UpdateUserTestNonExistentUser(){
//        RoleEntity role = RoleEntity.builder()
//                .roleName("TestRole")
//                .build();
//        UserEntity user = UserEntity.builder()
//                .id(1L)
//                .firstName("John")
//                .lastName("Doe")
//                .email("johndoe@example.com")
//                .phone(1234567890)
//                .driverLicense(12321312)
//                .role(role)
//                .build();
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            updateUsersUseCase.updateUser(UpdateUserRequest.builder()
//                    .id(user.getId())
//                    .phone(user.getPhone())
//                    .firstName(user.getFirstName())
//                    .lastName(user.getLastName())
//                    .email(user.getEmail())
//                    .driverLicense(user.getDriverLicense())
//                    .role(roleConverter.convert(role))
//                    .build()
//            );
//        });
//
//        assertEquals("No value present",exception.getMessage());
//    }

}