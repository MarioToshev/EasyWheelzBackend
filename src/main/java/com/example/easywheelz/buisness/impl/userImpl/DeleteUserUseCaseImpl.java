package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.userInterf.DeleteUserUseCase;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void deleteUser(long userId) {
        if (userRepository.existsById(userId)){
            userRepository.delete(userId);
        }
        else throw new RuntimeException("You are trying to delete user that doesnt exist");
    }
}
