package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.role.CreateRoleUseCase;
import com.example.easywheelz.configuration.security.isauthenticated.IsAuthenticated;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import jakarta.annotation.security.RolesAllowed;
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

    @PostMapping("")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateRoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        CreateRoleResponse response = createRoleUseCase.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
