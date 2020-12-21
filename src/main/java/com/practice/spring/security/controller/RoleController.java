package com.practice.spring.security.controller;

import com.practice.spring.security.api.RoleApi;
import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.BadRequestException;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoleController implements RoleApi {
    @Autowired
    private RoleService roleService;

    @Override
    public ResponseEntity<?> createRole(RoleDto roleDto) throws BadRequestException, ExistedDataException {
        try {
            log.info("Start API /role and method post");
            roleService.createRoles(roleDto);
            log.info("End with successful API /role and return the result");
        } catch (Exception e) {
            e.getStackTrace();
            log.info("End with exception API /role");
        }
        return ResponseEntity.ok(ResponseData.ofSuccess("message.api.invalid"));
    }
}
