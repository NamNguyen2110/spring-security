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
    private final VerificationTokenRepository verificationTokenRepo;

    @Override
    @Transactional
    public void save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationToken.setExpiryDate(calculateExpiryDate());
        verificationTokenRepo.save(verificationToken);
    }

    private Timestamp calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1440);
        return new Timestamp(calendar.getTime().getTime());
    }
}
