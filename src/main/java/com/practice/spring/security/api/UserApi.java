package com.practice.spring.security.api;

import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("Role Api")
@RequestMapping("/v1/api/users")
public interface UserApi {
    @PostMapping("")
    ResponseEntity<ResponseData> signUpUser(@RequestBody SignUpUserDto userDto);

    @PostMapping("/tokens")
    ResponseEntity<ResponseData> signInUser(@RequestBody SignInDto userDto);

}
