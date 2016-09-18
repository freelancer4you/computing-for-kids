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
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.services.UserActivityReport;

@Component
@Profile("development")
public class TestUserActivityReport implements UserActivityReport {

	private static final Logger LOGGER = LogManager.getLogger(TestUserActivityReport.class);

	private final PrepareUserMail prepareUserMail;

	@Autowired
	public TestUserActivityReport(final PrepareUserMail prepareUserMail) {
		this.prepareUserMail = Objects
				.requireNonNull(prepareUserMail, "Parameter 'prepareUserMail' darf nicht null sein.");
	}

	@Override
	public void registered(final User user, final Course course) throws IOException, MessagingException {
		final String adminMail = user + " registriert für " + course + " registriert.";
		final String userMail = prepareUserMail.prepare(user, course);
		LOGGER.info(userMail);
		LOGGER.info(adminMail);
	}

	@Override
	public void login(final User user) {
		LOGGER.info(user + " login.");
	}

	@Override
	public void logout(final User user) {
		LOGGER.info(user + " logout.");
	}


}
