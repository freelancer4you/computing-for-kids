package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.GoogleAccount;
import de.goldmann.apps.root.model.UserId;

public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, String> {
    UserId findByEmail(String email);
}
