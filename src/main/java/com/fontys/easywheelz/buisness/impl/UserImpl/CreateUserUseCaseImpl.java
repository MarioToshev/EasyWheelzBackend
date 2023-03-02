package com.fontys.easywheelz.buisness.impl.UserImpl;

import com.fontys.easywheelz.buisness.CreateUserUseCase;
import com.fontys.easywheelz.buisness.UserConverter;
import com.fontys.easywheelz.domain.User.CreateUserRequest;
import com.fontys.easywheelz.domain.User.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.fontys.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import com.fontys.easywheelz.persistance.impl.FakeUserRepositoryImpl;

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
