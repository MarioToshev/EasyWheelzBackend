package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserRepository userRepository;
    private final UserConverter converter;


    @Override
    public User getUser(long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(converter::convert).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByDisabledIs(false).stream().map(converter::convert)
                .toList();
    }
}
