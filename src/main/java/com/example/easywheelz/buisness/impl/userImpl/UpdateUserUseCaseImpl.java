package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.userInterf.UpdateUserUseCase;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final FakeUserRepositoryImpl userRepository;
    private final FakeRoleRepositoryImpl roleRepository;

    private final UserConverter converter;
    @Override
    public void updateUser(UpdateUserRequest request) {
        if (!roleRepository.existsByRoleName(request.getRole().getRoleName())){

            throw new RuntimeException("Role doesn't exist");
        }
        userRepository.update(converter.convert(request));
    }
}
