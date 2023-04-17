package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUsersUseCase getUsersUseCase;

    @PostMapping("")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(getUsersUseCase.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(getUsersUseCase.getUser(userId));
    }
    @PutMapping("")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request) {
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void > deleteUser(@PathVariable long userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
