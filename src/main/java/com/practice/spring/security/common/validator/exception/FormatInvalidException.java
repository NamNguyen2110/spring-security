package com.practice.spring.security.common.validator.exception;

public class FormatInvalidException extends RuntimeException {
    public static final String FORMAT_INVALID = "Wrong data types";

    public FormatInvalidException() {
        super();
    }

    public FormatInvalidException(String message) {
        super(message);
    }

    public FormatInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
