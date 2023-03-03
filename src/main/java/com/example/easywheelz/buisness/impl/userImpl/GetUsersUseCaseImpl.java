package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.userInterf.GetUsersUseCase;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.impl.FakeUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final FakeUserRepositoryImpl userRepository ;
    private final UserConverter converter;


    @Override
    public User getUser(long id) {
        return converter.convert(userRepository.findById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(user -> converter.convert(user))
                .collect(Collectors.toList());
    }
}
