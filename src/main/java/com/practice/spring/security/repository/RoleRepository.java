package com.practice.spring.security.repository;

import com.practice.spring.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    boolean existsByRoleName(String roleName);

    Optional<Role> findByRoleName(String roleName);
}
