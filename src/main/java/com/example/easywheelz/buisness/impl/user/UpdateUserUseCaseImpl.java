package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.buisness.interfaces.user.UpdateUserUseCase;
import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.domain.user.UpdateUserRequest;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserConverter converter;
    @Override
    public void updateUser(UpdateUserRequest request) {

        RoleEntity role = roleRepository.findByRoleName(request.getRole());
        UserEntity userfomBase = userRepository.findByEmail(request.getEmail());
        if (role == null){
            throw new IncorrectUserCredentialsError("Role doesn't exist");
        }
        UserEntity userToUpdate = converter.convert(request);
        userToUpdate.setId(userfomBase.getId());
        userToUpdate.setRole(role);
        userToUpdate.setPassword(userfomBase.getPassword());
        userToUpdate.setDisabled(userfomBase.getDisabled());
        userRepository.save(userToUpdate);
    }
}
