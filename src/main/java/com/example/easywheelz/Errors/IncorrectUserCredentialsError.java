package com.example.easywheelz.Errors;

public class IncorrectUserCredentialsError extends RuntimeException {
    public IncorrectUserCredentialsError(String errorMessage) {
        super(errorMessage);
    }
}
