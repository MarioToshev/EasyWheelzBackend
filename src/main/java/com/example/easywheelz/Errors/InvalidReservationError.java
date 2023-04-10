package com.example.easywheelz.Errors;

public class InvalidReservationError extends RuntimeException {
    public InvalidReservationError(String errorMessage) {
        super(errorMessage);
    }
}