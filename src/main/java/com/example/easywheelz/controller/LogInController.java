package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.user.LogInUseCase;
import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.domain.user.LogInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LogInController {
    private final LogInUseCase loginUseCase;
    @PostMapping
    public ResponseEntity<LogInResponse> login(@RequestBody @Valid LogInRequest loginRequest) {
        LogInResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
