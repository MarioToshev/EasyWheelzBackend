package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.interfaces.user.CreateUserUseCase;
import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.domain.user.CreateUserRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private RoleConverter roleConverter;
    private final UserConverter userConverter;
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        UserEntity user = userConverter.convert(request);

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("This email is already in use.");
        }
        if (userRepository.existsByPhone(request.getPhone())){
            throw new RuntimeException("This phone number is already in use.");
        }
       try{
           request.setRole(roleConverter.convert(roleRepository.findByRoleName("Customer")));
       }catch (Exception ex){
           throw new RuntimeException("Something went wrong");
       }

        return CreateUserResponse.builder().id(
                userRepository.save(userConverter.convert(request)).getId()
        ).build();
    }
}
