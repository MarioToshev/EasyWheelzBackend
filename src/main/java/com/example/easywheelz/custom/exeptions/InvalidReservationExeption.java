package com.example.easywheelz.custom.exeptions;

public class InvalidReservationExeption extends RuntimeException {
    public InvalidReservationExeption(String errorMessage) {
        super(errorMessage);
    }
}