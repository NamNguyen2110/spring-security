package com.practice.spring.security.service.impl;

import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;
import com.practice.spring.security.repository.VerificationTokenRepository;
import com.practice.spring.security.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;

@Service("verificationTokenService")
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository tokenRepo;

    @Override
    @Transactional
    public VerificationToken findByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    @Override
    @Transactional
    public VerificationToken findByUser(User user) {
        return tokenRepo.findByUser(user);
    }

    @Override
    public void save(User user, String token) {
//        VerificationToken token1 = new VerificationToken(user, token);
    }

    private Timestamp calculateExpiryDate(int expiryTimeInMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryTimeInMinute);
        return new Timestamp(calendar.getTime().getTime());
    }
}
