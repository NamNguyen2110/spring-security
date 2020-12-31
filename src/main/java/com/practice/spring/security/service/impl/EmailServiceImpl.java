package com.practice.spring.security.service.impl;

import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;
import com.practice.spring.security.repository.VerificationTokenRepository;
import com.practice.spring.security.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service("emailService")
@RequiredArgsConstructor
public class EmailServiceImpl extends MessageBundle implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final VerificationTokenRepository verificationTokenRepo;

    @Override
    public void sendEmail(User user) throws MessagingException {
        try {
            Optional<VerificationToken> optional = verificationTokenRepo.findByUser(user);
            if (optional.isPresent()) {
                throw new ExistedDataException(getMessage("User khong ton tai"));
            }
            String token = optional.get().getToken();
            Context context = new Context();
            context.setVariable("title", "Verify your email address");
            context.setVariable("link", "http://localhost:8080/v1/api/users/activation?token=" + token);
            String body = templateEngine.process("Verification", context);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Email address verification");
            helper.setText("ABC", body);
            javaMailSender.send(message);
        } catch (ExistedDataException e) {
            e.printStackTrace();
        }
    }
}
