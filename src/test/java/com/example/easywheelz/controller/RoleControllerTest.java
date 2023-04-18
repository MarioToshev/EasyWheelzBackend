package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.role.CreateRoleUseCase;
import com.example.easywheelz.buisness.interfaces.role.DeleteRoleUseCase;
import com.example.easywheelz.customExeptions.InvalidReservationExeption;
import com.example.easywheelz.customExeptions.InvalidRoleException;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.reservation.CreateReservationRequest;
import com.example.easywheelz.domain.reservation.CreateReservationResponse;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {
    @Mock
    private CreateRoleUseCase createRoleUseCase;
    @InjectMocks
    private RoleController roleController;


    @Test
    void createRoleTest(){


        CreateRoleRequest request = CreateRoleRequest.builder()
                .roleName("Customer")
                .build();


        CreateRoleResponse response = CreateRoleResponse.builder()
                .id(1L)
                .build();
        when(createRoleUseCase.createRole(request)).thenReturn(response);

        ResponseEntity<CreateRoleResponse> createRoleResponse = roleController.createRole(request);

        assertEquals(HttpStatus.CREATED, createRoleResponse.getStatusCode());
        assertEquals(1L, createRoleResponse.getBody().getId());
        verify(createRoleUseCase).createRole(request);
    }
    @Test
    void createReservationTestWrongData(){
        CreateRoleRequest request = CreateRoleRequest.builder()
                .roleName("Customer")
                .build();

        when(createRoleUseCase.createRole(request)).thenThrow(new InvalidRoleException(""));

        assertThrows(InvalidRoleException.class, () -> roleController.createRole(request));
        verify(createRoleUseCase).createRole(request);
    }
}