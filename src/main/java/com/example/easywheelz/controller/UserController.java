package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.userInterf.CreateUserUseCase;
import com.example.easywheelz.buisness.userInterf.GetUsersUseCase;
import com.example.easywheelz.buisness.userInterf.UpdateUserUseCase;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUsersUseCase getUsersUseCase;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(getUsersUseCase.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok(getUsersUseCase.getUser(id));
    }
    @PutMapping("")
    public ResponseEntity<String> UpdateUser(@RequestBody UpdateUserRequest request) {
        updateUserUseCase.updateUser(request);
        return ResponseEntity.ok(request.getId().toString());
    }
}
