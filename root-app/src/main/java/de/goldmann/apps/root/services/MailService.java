package de.goldmann.apps.root.services;

import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSenderImpl mailSender;

    private final SimpleMailMessage templateMessage;

    @Value("${spring.mail.from}")
    private String						from;

    @Autowired
    public MailService(final JavaMailSenderImpl mailSender, final SimpleMailMessage templateMessage) {
        this.mailSender = Objects.requireNonNull(mailSender, "mailSender");
        this.templateMessage = Objects.requireNonNull(templateMessage, "templateMessage");
    }

    public void sendHtmlMail(final String msgText, final String to, final String subject) throws MessagingException {
        final MimeMessage message = mailSender.createMimeMessage();

        // use the true flag to indicate you need a multipart message
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        // use the true flag to indicate the text included is HTML
        helper.setText(msgText, true);

        // Add a file if necessary
        // FileSystemResource res = new FileSystemResource(new
        // File("c:/Sample.jpg"));
        // helper.addInline("identifier1234", res);

        mailSender.send(message);
    }

    public void sendTextMail(final String msgText, final String to) {
        final SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo(to);
        msg.setText(msgText);
        mailSender.send(msg);
    }

}
