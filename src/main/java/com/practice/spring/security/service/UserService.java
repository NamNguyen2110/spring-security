package com.practice.spring.security.service;

import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;

public interface UserService {
    SignUpUserDto signUpUser(SignUpUserDto userDto);

    String generateToken(SignInDto signInDto);

    ResponseData activateUser(String token);
}
