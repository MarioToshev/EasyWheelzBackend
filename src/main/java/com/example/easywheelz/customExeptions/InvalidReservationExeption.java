package com.example.easywheelz.customExeptions;

public class InvalidReservationExeption extends RuntimeException {
    public InvalidReservationExeption(String errorMessage) {
        super(errorMessage);
    }
}