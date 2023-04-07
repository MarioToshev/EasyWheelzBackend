package com.example.easywheelz.Errors;

public class InvalidCarCredentals extends RuntimeException {
    public InvalidCarCredentals(String errorMessage) {
        super(errorMessage);
    }
}

