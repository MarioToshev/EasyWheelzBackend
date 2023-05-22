package com.example.easywheelz.custom.exeptions;

public class NoRoleExeption  extends  RuntimeException{
    public NoRoleExeption(String errorMessage) {
        super(errorMessage);
    }

}
