package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.userInterf.CreateUserUseCase;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
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
        if (!roleRepository.existsByRoleName(request.getRole().getRoleName())){

            throw new RuntimeException("Role doesn't exist");
        }

        return CreateUserResponse.builder().id(
                userRepository.save(converter.convert(request)).getId()
        ).build();
    }
}
