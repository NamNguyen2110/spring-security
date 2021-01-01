package com.practice.spring.security.service.impl;

import ch.qos.logback.classic.Logger;
import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.model.User;
import com.practice.spring.security.model.VerificationToken;
import com.practice.spring.security.repository.VerificationTokenRepository;
import com.practice.spring.security.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service("emailService")
@RequiredArgsConstructor
public class EmailServiceImpl extends MessageBundle implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final VerificationTokenRepository verificationTokenRepo;
    private Logger log = (Logger) LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(User user) {
        try {
            Optional<VerificationToken> optional = verificationTokenRepo.findByUser(user);
            if (optional.isPresent()) {
                String token = optional.get().getToken();
                Context context = new Context();
                context.setVariable("title", "Verify your email address");
                context.setVariable("link", "http://localhost:8080/v1/api/users/activation?token=" + token);
                String body = templateEngine.process("Verification", context);
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = null;
                helper = new MimeMessageHelper(message, true);
                helper.setTo(user.getEmail());
                helper.setSubject("Email address verification");
                helper.setText("", body);
                javaMailSender.send(message);
            }
        } catch (MessagingException e) {
            log.info("Email exception");
            e.printStackTrace();
        }
    }
}
