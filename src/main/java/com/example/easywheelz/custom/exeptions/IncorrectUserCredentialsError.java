package com.example.easywheelz.custom.exeptions;

public class IncorrectUserCredentialsError extends RuntimeException {

    public IncorrectUserCredentialsError(String errorMessage) {
        super(errorMessage);
    }
}
