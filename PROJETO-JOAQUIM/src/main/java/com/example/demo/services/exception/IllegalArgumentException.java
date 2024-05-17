package com.example.demo.services.exception;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String msg) {
        super(msg);
    }
}