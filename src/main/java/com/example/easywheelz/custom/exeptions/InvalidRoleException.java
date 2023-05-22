package com.example.easywheelz.custom.exeptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String errorMessage) {
        super(errorMessage);
    }
}
