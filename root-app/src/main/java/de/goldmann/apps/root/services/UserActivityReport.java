package de.goldmann.apps.root.services;

import java.io.IOException;

import javax.mail.MessagingException;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.UserId;

public interface UserActivityReport {

    void registered(UserId user, Course course) throws IOException, MessagingException;

    void login(UserId user);

    void logout(UserId user);
}
