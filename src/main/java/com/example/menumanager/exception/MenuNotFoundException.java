package com.example.menumanager.exception;

public class MenuNotFoundException extends RuntimeException{
    public MenuNotFoundException(String message) {
        super(message);
    }
}
