package de.goldmann.map.services;


import java.io.IOException;
import java.util.Objects;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.MailService;
import de.goldmann.apps.root.services.UserActivityReport;

@Component
@Profile("production")
public class DefaultUserActivityReport implements UserActivityReport {

    private static final String   EMAIL_ADMIN = "goldi23@freenet.de";

    private static final Logger LOGGER = LogManager.getLogger(DefaultUserActivityReport.class);

    private final MailService   mailService;

    private final PrepareUserMail prepareUserMail;

    @Autowired
    public DefaultUserActivityReport(final MailService mailService, final PrepareUserMail prepareUserMail) {
        this.mailService = Objects.requireNonNull(mailService, "Parameter 'mailService' darf nicht null sein.");
        this.prepareUserMail = Objects
                .requireNonNull(prepareUserMail, "Parameter 'prepareUserMail' darf nicht null sein.");
    }

    @Override
    public void registered(final UserId user, final Course course) throws IOException, MessagingException {
        final String adminMail = user + " registriert für " + course + " registriert.";
        final String userMail = prepareUserMail.prepare(user, course);
        mailService.sendHtmlMail(userMail, user.getEmail(), "Ihre Kursbuchung bei Computing-For-Kids");
        mailService.sendTextMail(adminMail, EMAIL_ADMIN);
        LOGGER.info(userMail);
        LOGGER.info(adminMail);
    }

    @Override
    public void login(final UserId user) {
        LOGGER.info(user + " login.");
    }

    @Override
    public void logout(final UserId user) {
        LOGGER.info(user + " logout.");
    }


}
