package com.practice.spring.security.controller;

import com.practice.spring.security.api.RoleApi;
import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.respond.ResponseData;
import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;
import com.practice.spring.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {
    Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    @Override
    public ResponseEntity<ResponseData> createRole(List<RoleDto> roleDto) throws ExistedDataException, InputRequestException {
            List<RoleDto> data = roleService.createRoles(roleDto);
            return ResponseEntity.ok(ResponseData.ofSuccess(MessageBundle.getMessage("message.api.success"), data));

    }
}
