package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.converters.RoleConverter;
import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.buisness.impl.PasswordValidator;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private RoleConverter roleConverter;
    private final UserConverter userConverter;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IncorrectUserCredentialsError("This email is already in use.");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new IncorrectUserCredentialsError("This phone number is already in use.");
        }
        if (PasswordValidator.isValid(request.getPassword())) {
            throw new IncorrectUserCredentialsError("Password must contain at least  8 characters and a 1 special symbol");
        }
        RoleEntity role = roleRepository.findByRoleName("Customer");
        if (role == null) {
            throw new IncorrectUserCredentialsError("There was an error creating ur account");
        }

        request.setRole(roleConverter.convert(role));

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);


        return CreateUserResponse.builder().id(
                userRepository.save(userConverter.convert(request)).getId()
        ).build();
    }
}
