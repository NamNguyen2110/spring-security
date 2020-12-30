package com.practice.spring.security.service;

import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;

import java.util.List;

public interface RoleService {
    List<RoleDto> createRoles(List<RoleDto> roleDto) throws ExistedDataException, InputRequestException;
}
