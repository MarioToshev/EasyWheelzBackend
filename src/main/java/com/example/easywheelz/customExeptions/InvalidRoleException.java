package com.example.easywheelz.customExeptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String errorMessage) {
        super(errorMessage);
    }
}
