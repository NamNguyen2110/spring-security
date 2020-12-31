package com.practice.spring.security.controller;

import com.practice.spring.security.api.UserApi;
import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController extends MessageBundle implements UserApi {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Override
    public ResponseEntity<ResponseData> signUpUser(SignUpUserDto userDto) {
        userService.signUpUser(userDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(getMessage("message.api.success")));
    }

    @Override
    public ResponseEntity<ResponseData> signInUser(SignInDto userDto) {
        String token = userService.generateToken(userDto);
        return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("message.api.success"), token));
    }

    @Override
    public ResponseEntity<ResponseData> activateUser(String token) {
        return ResponseEntity.ok(userService.activateUser(token));
    }

}
