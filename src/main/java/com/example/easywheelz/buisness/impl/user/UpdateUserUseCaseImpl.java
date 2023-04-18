package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.customExeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserConverter converter;
    @Override
    public void updateUser(UpdateUserRequest request) {
        if (!roleRepository.existsById(request.getRole().getId())){

            throw new IncorrectUserCredentialsError("Role doesn't exist");
        }
        userRepository.save(converter.convert(request));
    }
}
