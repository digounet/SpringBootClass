package com.example.exceptions;

public class RequiredParameterException extends Exception {
    public RequiredParameterException(String message) {
        super(message);
    }
}
