package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AllArgsConstructor
class DeleteUserUseCaseImplTest {

    @Autowired
    private  DeleteUserUseCase deleteUserUseCase;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Test
     void testDeleteUserById() {

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
        long userId = userRepository.save(request).getId();
        deleteUserUseCase.deleteUser(userId);
        assertEquals(Collections.emptyList(), userRepository.findAll());
    }
    @Test
     void testDeleteUserByNonExistentId() {

        long userId = 2;

        Exception exception = assertThrows(RuntimeException.class, () -> {
            deleteUserUseCase.deleteUser(userId);
        });

        assertEquals("You are trying to delete user that doesnt exist", exception.getMessage());
    }


}