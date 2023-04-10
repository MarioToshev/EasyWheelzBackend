package com.example.easywheelz.Errors;

public class InvalidCarCredentials extends RuntimeException {
    public InvalidCarCredentials(String errorMessage) {
        super(errorMessage);
    }
}

