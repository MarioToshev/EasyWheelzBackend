package com.example.easywheelz.customExeptions;

public class NoRoleExeption  extends  RuntimeException{
    public NoRoleExeption(String errorMessage) {
        super(errorMessage);
    }

}
