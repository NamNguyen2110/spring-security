package com.practice.spring.security.service;

import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;

public interface UserService {
    SignUpUserDto signUpUser(SignUpUserDto userDto) throws ExistedDataException, InputRequestException;

    String generateToken(SignInDto signInDto);

    ResponseData activateUser(String token);
}
