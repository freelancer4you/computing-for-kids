package de.goldmann.apps.root.services;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private final MailSender              mailSender;
	private final SimpleMailMessage templateMessage;

	@Autowired
	public MailService(final MailSender mailSender, final SimpleMailMessage templateMessage) {
		this.mailSender = Objects.requireNonNull(mailSender, "mailSender");
		this.templateMessage = Objects.requireNonNull(templateMessage, "templateMessage");
	}

	public void sendMail(final String msgText, final String to) {
		final SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
		msg.setTo(to);
		msg.setText(msgText);
		mailSender.send(msg);
	}

}
