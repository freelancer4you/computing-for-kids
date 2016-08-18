package de.goldmann.apps.root.services;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger     LOGGER  = LogManager.getLogger(MailService.class);

    private static final String     MAIL_TO = "goldi";
    private MailSender              mailSender;
    private final SimpleMailMessage templateMessage;

    @Autowired
    public MailService(final MailSender mailSender, final SimpleMailMessage templateMessage) {
        this.mailSender = Objects.requireNonNull(mailSender, "mailSender");
        this.templateMessage = Objects.requireNonNull(templateMessage, "templateMessage");
    }

    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String msgText) {
        final SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(MAIL_TO);
        msg.setText(msgText);
        try {
            this.mailSender.send(msg);
        }
        catch (final MailException ex) {
            LOGGER.error("Fehler beim Mail-Versand:[msg=" + msgText + "]", ex);
        }
    }

}
