package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.interfaces.accessToken.AccessTokenEncoder;
import com.example.easywheelz.buisness.interfaces.user.LogInUseCase;
import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.domain.AccessToken;
import com.example.easywheelz.domain.user.LogInRequest;
import com.example.easywheelz.domain.user.LogInResponse;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LogInUseCaseImpl implements LogInUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LogInResponse login(LogInRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new IncorrectUserCredentialsError("Invalid credentials");
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new IncorrectUserCredentialsError("Invalid credentials");
        }

        String accessToken = generateAccessToken(user);
        return LogInResponse.builder().accessToken(accessToken).build();
    } private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().getRoleName());


        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userId(user.getId())
                        .build());
    }
}
