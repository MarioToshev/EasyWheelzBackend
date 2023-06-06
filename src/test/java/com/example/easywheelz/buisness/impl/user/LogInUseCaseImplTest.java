package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.interfaces.accesstoken.AccessTokenEncoder;
import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.domain.AccessToken;
import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogInUseCaseImplTest {
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  AccessTokenEncoder accessTokenEncoder;
    @InjectMocks
    private LogInUseCaseImpl service;

    @Test
    void loginTest(){

        LogInRequest request   = LogInRequest.builder()
                .email("m@m.m")
                .password("123").build();


        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(
                        UserEntity.builder()
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .role(RoleEntity.builder().roleName("admin").build())
                                .disabled(false)
                                .build()
                );
        when(passwordEncoder.matches(request.getPassword(),request.getPassword())).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessToken.class))).thenReturn("eykasidjasoidjaopsdj");

       var response = service.login(request);

        assertEquals("eykasidjasoidjaopsdj",response.getAccessToken());
        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder).matches(request.getPassword(),request.getPassword());
        verify(accessTokenEncoder).encode(any(AccessToken.class));
    }
    @Test
    void loginTestDisabled(){

        LogInRequest request   = LogInRequest.builder()
                .email("m@m.m")
                .password("123").build();


        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(
                        UserEntity.builder()
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .role(RoleEntity.builder().roleName("admin").build())
                                .disabled(true)
                                .build()
                );

        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            service.login(request);
        });
        assertEquals("Invalid credentials",exception.getMessage());
        verify(userRepository).findByEmail(request.getEmail());
    }

    @Test
    void loginTestIncorrectEmail(){

        LogInRequest request   = LogInRequest.builder()
                .email("m@m.m")
                .password("123").build();


        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(null);


        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
           service.login(request);
        });
        assertEquals("Invalid credentials",exception.getMessage());
        verify(userRepository).findByEmail(request.getEmail());
    }
    @Test
    void loginTestIncorrectPassword(){

        LogInRequest request   = LogInRequest.builder()
                .email("m@m.m")
                .password("123").build();


        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(
                        UserEntity.builder()
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .role(RoleEntity.builder().roleName("admin").build())
                                .disabled(false)
                                .build()
                );
        when(passwordEncoder.matches(request.getPassword(),request.getPassword())).thenReturn(false);


        Exception exception = assertThrows(IncorrectUserCredentialsError.class, () -> {
            service.login(request);
        });
        assertEquals("Invalid credentials",exception.getMessage());
        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder).matches(request.getPassword(),request.getPassword());
    }


}