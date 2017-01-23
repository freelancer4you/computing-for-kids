package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.GoogleAccount;

public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, String> {
    GoogleAccount findByEmail(String email);
}
