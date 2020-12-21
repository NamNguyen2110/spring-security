package com.practice.spring.security.common.validator;


import com.practice.spring.security.common.validator.exception.FormatInvalidException;

public interface ValidatorService {
    ValidatorMessage process(ValidateObject obj) throws FormatInvalidException;
}
