package com.example.easywheelz.Errors;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String errorMessage) {
        super(errorMessage);
    }
}
