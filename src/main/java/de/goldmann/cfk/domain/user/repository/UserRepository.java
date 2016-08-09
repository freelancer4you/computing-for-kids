/*
 * Created: 09.08.2016
 * Copyright (c) 2005-2016 Saxess AG. All rights reserved.
 */
package de.goldmann.cfk.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.cfk.domain.user.entity.User;

/**
 * @author goldmann
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
