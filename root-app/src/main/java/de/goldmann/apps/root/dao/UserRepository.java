package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.DefaultAccount;

public interface UserRepository extends JpaRepository<DefaultAccount, String> {
    DefaultAccount findByEmail(String email);
}
