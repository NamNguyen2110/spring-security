package com.practice.spring.security.exception;

public class ExistedDataException extends Exception {
    public ExistedDataException(String message) {
        super(message);
    }
}
