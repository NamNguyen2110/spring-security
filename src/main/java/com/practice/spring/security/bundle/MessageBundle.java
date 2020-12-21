package com.practice.spring.security.bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageBundle {
    private static final Logger log = LoggerFactory.getLogger(MessageBundle.class);
    private static final String BASE_NAME = "message";

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, (Object[]) null, locale);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);

        String message;
        try {
            message = resourceBundle.getString(code);
        } catch (MissingResourceException var6) {
            log.debug(var6.getMessage(), var6);
            message = code;
        }

        return MessageFormat.format(message, args);
    }

    public static String getMessage(String code) {
        return getMessage(code, (Object[]) null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
