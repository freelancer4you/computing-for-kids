package de.goldmann.apps.root.services;

import java.io.IOException;

import javax.mail.MessagingException;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.User;

public interface UserActivityReport {

    void registered(User user, Course course) throws IOException, MessagingException;

    void login(User user);

    void logout(User user);
}
