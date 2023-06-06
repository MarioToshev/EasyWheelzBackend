package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.custom.exeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void deleteUser(long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setDisabled(true);
            userRepository.save(user.get());
        } else throw new IncorrectUserCredentialsError("You are trying to delete user that doesnt exist");
    }
}
