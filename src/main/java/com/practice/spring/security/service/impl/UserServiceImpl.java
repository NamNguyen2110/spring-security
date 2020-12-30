package com.practice.spring.security.service.impl;

import com.practice.spring.security.bundle.MessageBundle;
import com.practice.spring.security.common.utils.CommonUtils;
import com.practice.spring.security.common.validator.ValidatorBuilder;
import com.practice.spring.security.common.validator.ValidatorGroup;
import com.practice.spring.security.common.validator.ValidatorMessage;
import com.practice.spring.security.common.validator.group.RegexContant;
import com.practice.spring.security.dto.SignInDto;
import com.practice.spring.security.dto.SignUpUserDto;
import com.practice.spring.security.exception.ExistedDataException;
import com.practice.spring.security.exception.InputRequestException;
import com.practice.spring.security.model.Author;
import com.practice.spring.security.model.Role;
import com.practice.spring.security.model.User;
import com.practice.spring.security.repository.AuthorRepository;
import com.practice.spring.security.repository.RoleRepository;
import com.practice.spring.security.repository.UserRepository;
import com.practice.spring.security.repository.VerificationTokenRepository;
import com.practice.spring.security.security.TokenProcess;
import com.practice.spring.security.service.EmailService;
import com.practice.spring.security.service.UserService;
import com.practice.spring.security.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl extends MessageBundle implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RoleRepository roleRepo;
    private final AuthorRepository authorRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final TokenProcess tokenProcess;
//    private final VerificationTokenRepository verificationTokenRepo;
//    private final VerificationTokenService verificationTokenService;
//    private final EmailService emailService;

    @Override
    @Transactional
    public SignUpUserDto signUpUser(SignUpUserDto userDto) {
        try {
            validateSignUpUser(userDto);
            Author author = new Author();
            if (userDto.getRoleName().isEmpty()) {
                Role userRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_USER))
                        .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                author.setRole(userRole);
            } else {
                switch (userDto.getRoleName()) {
                    case "admin":
                        Role adminRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_ADMIN))
                                .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                        author.setRole(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_MOD))
                                .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                        author.setRole(modRole);
                        break;
                    case "tester":
                        Role testRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_TEST))
                                .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                        author.setRole(testRole);
                        break;
                    case "inspector":
                        Role inspectorRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_INSPECTOR))
                                .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                        author.setRole(inspectorRole);
                        break;

                    default:
                        Role defaultRole = roleRepo.findByRoleName(String.valueOf(Role.ERole.ROLE_USER))
                                .orElseThrow(() -> new InputRequestException(getMessage("message.role.not.existed")));
                        author.setRole(defaultRole);
                        break;
                }
            }
            User user = new User(userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    "0", userDto.getEmail());
            userRepo.save(user);
            author.setUsers(user);
            authorRepo.save(author);
            logger.info("Waiting a confirmation email.....");
            // Email confirmation
//            String token = UUID.randomUUID().toString();
//            verificationTokenService.save(user, token);
//            emailService.sendEmail(user);
            return userDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateToken(SignInDto signInDto) {
        try {
            validateSignInUser(signInDto);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getPassword(), signInDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProcess.generateToken(authentication);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Username or Password wrong");
        }
        return null;
    }

    private void validateSignUpUser(SignUpUserDto userDto) throws InputRequestException, ExistedDataException {
        ValidatorBuilder builder = new ValidatorBuilder();
        builder
                .push(new ValidatorGroup().ofFieldName("username")
                        .ofFieldName(userDto.getUsername())
                        .ofRequired()
                        .ofMaxLength(10)
                        .ofMinLength(5)
                        .ofRegex(RegexContant.USERNAME_REGEX))
                .push(new ValidatorGroup().ofFieldName("password")
                        .ofFieldName(userDto.getPassword())
                        .ofRequired()
                        .ofMaxLength(10)
                        .ofMinLength(5)
                        .ofRegex(RegexContant.USERNAME_REGEX))
                .push(new ValidatorGroup().ofFieldName("email")
                        .ofFieldName(userDto.getEmail())
                        .ofRequired()
                        .ofRegex(RegexContant.EMAIL_REGEX));
        List<ValidatorMessage> validatorMessages = builder.process();
        if (CommonUtils.isNullOrEmpty(validatorMessages)) {
            throw new InputRequestException(getMessage("message.null"));
        }
        if (userRepo.existsByUsername(userDto.getUsername())) {
            throw new ExistedDataException(getMessage("Data existed"));
        }
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new ExistedDataException(getMessage("Data existed"));
        }

    }

    private void validateSignInUser(SignInDto userDto) throws InputRequestException {
        ValidatorBuilder builder = new ValidatorBuilder();
        builder
                .push(new ValidatorGroup().ofFieldName("username")
                        .ofFieldName(userDto.getEmail())
                        .ofRequired()
                        .ofMaxLength(10)
                        .ofMinLength(5)
                        .ofRegex(RegexContant.USERNAME_REGEX))
                .push(new ValidatorGroup().ofFieldName("password")
                        .ofFieldName(userDto.getPassword())
                        .ofRequired()
                        .ofMaxLength(10)
                        .ofMinLength(5)
                        .ofRegex(RegexContant.USERNAME_REGEX));
        List<ValidatorMessage> validatorMessages = builder.process();
        if (CommonUtils.isNullOrEmpty(validatorMessages)) {
            throw new InputRequestException(getMessage("message.null"));
        }
    }
}
