package de.goldmann.apps.root.dao;

import org.springframework.data.repository.CrudRepository;

import de.goldmann.apps.root.model.User;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findUserByUsername(String username);
}
