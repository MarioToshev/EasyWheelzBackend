package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.user.LogInUseCase;
import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.domain.user.LogInResponse;
import org.apache.http.auth.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogInControllerTest {

    @Mock
    private  LogInUseCase loginUseCase;
    @InjectMocks
    private LogInController controller;

    @Test
    void Login(){
        LogInRequest request = LogInRequest.builder().email("m@m.m").password("123").build();

        when(loginUseCase.login(request)).thenReturn(LogInResponse.builder()
                .accessToken("wqpoieokqpqwxcds")
                .build());

        var response = controller.login(request);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("wqpoieokqpqwxcds", Objects.requireNonNull(response.getBody()).getAccessToken());
        verify(loginUseCase).login(request);
    }
    @Test
    void LoginIncorrectUser() throws InvalidCredentialsException {
        controller.login(null);
    }


}