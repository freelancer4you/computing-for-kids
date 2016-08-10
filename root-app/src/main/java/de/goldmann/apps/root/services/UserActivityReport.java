package de.goldmann.apps.root.services;

import de.goldmann.apps.root.model.User;

public interface UserActivityReport {

    void registered(User user);

    void login(User user);

    void logout(User user);
}
