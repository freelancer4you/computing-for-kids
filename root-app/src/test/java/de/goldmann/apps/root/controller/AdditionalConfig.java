package de.goldmann.apps.root.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.UserActivityReport;

public class AdditionalConfig {

    @Bean
    JavaMailSenderImpl mailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    SimpleMailMessage mailMessage() {
        return new SimpleMailMessage();
    }

    @Bean
    UserActivityReport userActivityReport() {
        return new UserActivityReport() {

            @Override
            public void logout(final UserId user) {
                // TODO Auto-generated method stub

            }

            @Override
            public void login(final UserId user) {
                // TODO Auto-generated method stub

            }

            @Override
            public void registered(final UserId user, final Course course) {
                // TODO Auto-generated method stub

            }
        };
    }

}
