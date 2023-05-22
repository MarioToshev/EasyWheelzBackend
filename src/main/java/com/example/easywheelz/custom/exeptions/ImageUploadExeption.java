package com.example.easywheelz.custom.exeptions;

public class ImageUploadExeption extends RuntimeException{
    public ImageUploadExeption(String errorMessage) {
        super(errorMessage);
    }
}
