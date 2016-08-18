package de.goldmann.map.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.services.UserActivityReport;

@Component
public class DefaultUserActivityReport implements UserActivityReport {

    private static final Logger LOGGER = LogManager.getLogger(DefaultUserActivityReport.class);

    @Override
	public void registered(final User user) {
        LOGGER.info(user + " registriert.");
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
