package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
