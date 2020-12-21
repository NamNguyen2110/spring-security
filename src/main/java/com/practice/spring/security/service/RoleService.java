package com.practice.spring.security.service;

import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.BadRequestException;
import com.practice.spring.security.exception.ExistedDataException;

public interface RoleService {
    void createRoles(RoleDto roleDto) throws ExistedDataException, BadRequestException;

}
