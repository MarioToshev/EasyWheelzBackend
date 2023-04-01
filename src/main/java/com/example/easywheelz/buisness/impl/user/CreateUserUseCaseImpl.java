package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
