package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.CreateUserUseCase;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final FakeUserRepositoryImpl userRepository;
    private final FakeRoleRepositoryImpl roleRepository;

    private final UserConverter converter;
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (!roleRepository.existsById(request.getRole().getId())){

            throw new RuntimeException("Role doesn't exist");
        }

        return CreateUserResponse.builder().id(
                userRepository.save(converter.convert(request)).getId()
        ).build();
    }
}
