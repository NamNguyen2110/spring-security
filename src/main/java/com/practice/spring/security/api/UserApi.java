package com.practice.spring.security.api;

import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Role Api")
@RequestMapping("/v1/api/users")
public interface UserApi {
    @PostMapping("")
    ResponseEntity<ResponseData> signUpUser(@RequestBody SignUpUserDto userDto) throws ExistedDataException, InputRequestException;

    @PostMapping("/tokens")
    ResponseEntity<ResponseData> signInUser(@RequestBody SignInDto userDto);

    @GetMapping("/activation")
    ResponseEntity<ResponseData> activateUser(@RequestParam("token") String token);
}
