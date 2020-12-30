package com.practice.spring.security.service;

import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.exception.InputRequestException;

public interface UserService {
    SignUpUserDto signUpUser(SignUpUserDto userDto);

    String generateToken(SignInDto signInDto);
}
