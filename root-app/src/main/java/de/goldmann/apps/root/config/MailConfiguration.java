package de.goldmann.apps.root.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import de.goldmann.apps.root.security.PasswordUtils;

@Configuration
public class MailConfiguration {

	@Bean
	JavaMailSenderImpl mailSender(@Value("${spring.mail.host}") final String host,
			@Value("${spring.mail.password}") final String password,
			@Value("${spring.mail.username}") final String username, @Value("${spring.mail.port}") final int port)
					throws GeneralSecurityException, IOException {
		final JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		// javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPassword(PasswordUtils.decrypt(password));

		final Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		javaMailSenderImpl.setJavaMailProperties(props);
		javaMailSenderImpl.setUsername(username);
		return javaMailSenderImpl;
	}

	@Bean
	SimpleMailMessage mailMessage(@Value("${spring.mail.from}") final String from,
			@Value("${spring.mail.subject}") final String subject) {
		final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		return simpleMailMessage;
	}
}
