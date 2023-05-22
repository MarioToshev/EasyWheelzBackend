package com.example.easywheelz.customExeptions;

public class ImageUploadExeption extends RuntimeException{
    public ImageUploadExeption(String errorMessage) {
        super(errorMessage);
    }
}
