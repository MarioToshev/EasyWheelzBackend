package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.roleInterf.CreateRoleUseCase;
import com.example.easywheelz.buisness.userInterf.CreateUserUseCase;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;

    @PostMapping("/create")
    public ResponseEntity<CreateRoleResponse> createUser(@RequestBody CreateRoleRequest request) {
        CreateRoleResponse response = createRoleUseCase.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
