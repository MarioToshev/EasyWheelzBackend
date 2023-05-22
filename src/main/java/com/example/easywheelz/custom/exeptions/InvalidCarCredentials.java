package com.example.easywheelz.custom.exeptions;

public class InvalidCarCredentials extends RuntimeException {
    public InvalidCarCredentials(String errorMessage) {
        super(errorMessage);
    }
}

