package com.practice.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDto extends SignInDto {
    private String username;
    private String roleName;
}
