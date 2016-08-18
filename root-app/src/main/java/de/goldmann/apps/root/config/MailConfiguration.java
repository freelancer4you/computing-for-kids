package de.goldmann.apps.root.config;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import de.goldmann.apps.root.security.PasswordUtils;

@Configuration
public class MailConfiguration {

    @Bean
    JavaMailSenderImpl mailSender(@Value("${mail.host}") final String host,
            @Value("${mail.password}") final String password, @Value("${mail.username}") final String username) throws GeneralSecurityException, IOException {
        final JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setPassword(PasswordUtils.decrypt(password));
        javaMailSenderImpl.setUsername(username);
        return javaMailSenderImpl;
    }

    @Bean
    SimpleMailMessage mailMessage(@Value("${mail.from}") final String from,
            @Value("${mail.subject}") final String subject) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        return simpleMailMessage;
    }
}
