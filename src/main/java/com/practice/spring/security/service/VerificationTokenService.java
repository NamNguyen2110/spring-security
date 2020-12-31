package com.practice.spring.security.service;

import com.practice.spring.security.model.User;

public interface VerificationTokenService {
    void save(User user, String token);
}
