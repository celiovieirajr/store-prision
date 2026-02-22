package com.example.prision.exceptions;

public class RequiredField extends RuntimeException {
    public RequiredField(String message) {
        super(message);
    }
}
