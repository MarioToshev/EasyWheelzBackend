package com.example.easywheelz.buisness.impl;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z]).{5}$";

    public static boolean isValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }
}
