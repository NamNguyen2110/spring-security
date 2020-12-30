package com.practice.spring.security.service;

import com.practice.spring.security.model.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(User user) throws MessagingException;
}
