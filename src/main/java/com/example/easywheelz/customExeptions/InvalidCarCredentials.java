package com.example.easywheelz.customExeptions;

public class InvalidCarCredentials extends RuntimeException {
    public InvalidCarCredentials(String errorMessage) {
        super(errorMessage);
    }
}

