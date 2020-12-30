package com.practice.spring.security.service;

import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

    void save(User user, String token);
}
