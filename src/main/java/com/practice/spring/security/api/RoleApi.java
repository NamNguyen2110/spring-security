package com.practice.spring.security.api;

import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.BadRequestException;
import com.practice.spring.security.exception.ExistedDataException;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("Role Api")
@RequestMapping("/v1/api/users/roles")
public interface RoleApi {
    @PostMapping("")
    ResponseEntity<?> createRole(@RequestBody RoleDto roleDto) throws BadRequestException, ExistedDataException;
}
