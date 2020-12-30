package com.practice.spring.security.service.impl;

import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;
import com.practice.spring.security.service.EmailService;
import com.practice.spring.security.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("emailService")
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final VerificationTokenService tokenService;
//    private final TemplateEngine templateEngine;
//    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(User user) throws MessagingException {
//        VerificationToken verificationToken = tokenService.findByUser(user);
//        if (verificationToken != null) {
//            String token = verificationToken.getToken();
//            Context context = new Context();
//            context.setVariable("title", "Verify your email address");
//            context.setVariable("link", "http://localhost:3306/activation?token=" + token);
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(user.getEmail());
//            helper.setSubject("Email address verification");
//            helper.setText("ABC", "true");
//            javaMailSender.send(message);
//        }
    }
}
