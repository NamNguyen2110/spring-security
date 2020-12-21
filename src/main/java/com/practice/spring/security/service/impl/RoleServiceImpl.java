package com.practice.spring.security.service.impl;

import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.BadRequestException;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.model.Role;
import com.practice.spring.security.repository.RoleRepository;
import com.practice.spring.security.service.RoleService;
import com.practice.spring.security.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void createRoles(RoleDto roleDto) throws ExistedDataException, BadRequestException {
        if (roleRepo.existsByRole(roleDto.getRole())) {
            throw new ExistedDataException("Existed");
        }
        if (roleDto.getRole().startsWith("ROLE_")) {
            throw new BadRequestException("Error");
        }
        Role role = ObjectMapperUtils.toDto(roleDto, Role.class);
        roleRepo.save(role);
    }
}
