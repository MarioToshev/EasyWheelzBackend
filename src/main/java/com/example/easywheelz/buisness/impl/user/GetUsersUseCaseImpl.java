package com.example.easywheelz.buisness.impl.userImpl;

import com.example.easywheelz.buisness.UserConverter;
import com.example.easywheelz.buisness.userInterf.GetUsersUseCase;
import com.example.easywheelz.domain.user.User;
import com.example.easywheelz.persistance.UserRepository;
import com.example.easywheelz.persistance.entities.UserEntity;
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
        var result = userRepository.findById(id);
        if (result != null){
            return converter.convert(result);
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
