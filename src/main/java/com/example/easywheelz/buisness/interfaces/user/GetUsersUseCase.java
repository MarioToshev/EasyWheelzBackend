package com.example.easywheelz.buisness.userInterf;

import com.example.easywheelz.domain.user.User;

import java.util.List;

public interface GetUsersUseCase {
    public User getUser(long id);
    public List<User> getAllUsers();
}
