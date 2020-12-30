package com.practice.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputRequestException extends Exception {
    public InputRequestException(String message) {
        super(message);
    }
}
