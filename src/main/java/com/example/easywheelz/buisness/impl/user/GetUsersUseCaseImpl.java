package com.example.easywheelz.buisness.impl.user;

import com.example.easywheelz.buisness.converters.UserConverter;
import com.example.easywheelz.buisness.interfaces.user.GetUsersUseCase;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserRepository userRepository ;
    private final UserConverter converter;


    @Override
    public User getUser(long id) {
        if (userRepository.existsById(id)){
            return converter.convert(userRepository.getReferenceById(id));
        }
        else
            return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(converter::convert)
                .toList();
    }
}
