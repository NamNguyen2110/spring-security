package com.practice.spring.security.repository;

import com.practice.spring.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByRole(String roleName);
}
