package com.practice.spring.security.repository;

import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
