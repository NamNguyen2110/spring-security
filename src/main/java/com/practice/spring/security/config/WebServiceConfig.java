package com.practice.spring.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfig implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(WebServiceConfig.class);
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        long maxAgeInSeconds = 3600;
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                // notice: enable in some swagger url access to swagger interface
                .allowCredentials(false)
                .maxAge(maxAgeInSeconds);
        logger.info("DISABLE CREDENTIALS");
    }

}
