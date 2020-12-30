package com.practice.spring.security.controller;

import com.practice.spring.security.api.UserApi;
import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.security.TokenProcess;
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
public class UserController implements UserApi {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final TokenProcess tokenProcess;

    @Override
    public ResponseEntity<ResponseData> signUpUser(SignUpUserDto userDto) {
//        try {
            userService.signUpUser(userDto);
            return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("message.api.success"), userDto));
//        } catch (Exception e) {
//            logger.info("Sign up API exception");
//            return ResponseEntity.ok(ResponseData.ofFail(MessageBundle.getMessage("message.server")));
//        }
    }

    @Override
    public ResponseEntity<ResponseData> signInUser(SignInDto userDto) {
//        try {
            String token = userService.generateToken(userDto);
            return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("message.api.success"), token));
//        } catch (Exception e) {
//            logger.info("Sign in API exception");
//            return ResponseEntity.ok(ResponseData.ofFail(MessageBundle.getMessage("message.server")));
//        }
    }

}
