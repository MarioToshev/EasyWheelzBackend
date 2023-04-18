package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.role.CreateRoleUseCase;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;

    @PostMapping("")
    public ResponseEntity<CreateRoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        CreateRoleResponse response = createRoleUseCase.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
