package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.customExeptions.IncorrectUserCredentialsError;
import com.example.easywheelz.buisness.interfaces.user.DeleteUserUseCase;
import com.example.easywheelz.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void deleteUser(long userId) {
        if (userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        }
        else throw new IncorrectUserCredentialsError("You are trying to delete user that doesnt exist");
    }
}
