package com.practice.spring.security.service.impl;

import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.validator.group.RegexContant;
import com.practice.spring.security.dto.RoleDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;
import com.practice.spring.security.model.Role;
import com.practice.spring.security.repository.RoleRepository;
import com.practice.spring.security.service.RoleService;
import com.practice.spring.security.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl extends MessageBundle implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepo;

    @Override
    public List<RoleDto> createRoles(List<RoleDto> roleDto) throws ExistedDataException, InputRequestException {
        List<RoleDto> dataList = new ArrayList<>();
        for (RoleDto dto : roleDto) {
            if (roleRepo.existsByRoleName(dto.getRoleName())) {
                throw new ExistedDataException(getMessage("message.existed"));
            }
            if (dto.getRoleName().startsWith(RegexContant.ROLE)) {
                throw new InputRequestException(getMessage("message.role.invalid"));
            }
            Role role = ObjectMapperUtils.toDto(dto, Role.class);
            roleRepo.save(role);
            dataList.add(ObjectMapperUtils.toEntity(role, RoleDto.class));
        }
        return dataList;
    }

}
