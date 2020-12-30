package com.practice.spring.security.api;

import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.InputRequestException;
import com.practice.spring.security.exception.ExistedDataException;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api("Role Api")
@RequestMapping("/v1/api/users/roles")
public interface RoleApi {
    @PostMapping("")
    ResponseEntity<ResponseData> createRole(@RequestBody List<RoleDto> roleDto) throws ExistedDataException, InputRequestException;
}
