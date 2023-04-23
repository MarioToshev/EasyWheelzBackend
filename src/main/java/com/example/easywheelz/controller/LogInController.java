package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.user.LogInUseCase;
import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.domain.user.LogInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LogInController {
    private final LogInUseCase loginUseCase;
    @PostMapping
    public ResponseEntity<LogInResponse> login(@RequestBody @Valid LogInRequest loginRequest) {
        LogInResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
