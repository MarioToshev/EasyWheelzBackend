package com.example.easywheelz.customExeptions;

public class IncorrectUserCredentialsError extends RuntimeException {

    public IncorrectUserCredentialsError(String errorMessage) {
        super(errorMessage);
    }
}
