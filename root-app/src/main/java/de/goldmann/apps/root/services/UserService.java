package de.goldmann.apps.root.services;

import static de.goldmann.apps.root.services.ValidationUtils.assertMatches;
import static de.goldmann.apps.root.services.ValidationUtils.assertNotBlank;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.User;

@Service
public class UserService {

    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository,
                "Parameter 'userRepository' darf nicht null sein.");
    }

    /**
     *
     * creates a new {@link User} in the database
     *
     * @return
     *
     */
    @Transactional
    public User createUser(final UserDTO userDto) {
        final String email = userDto.getEmail();
        assertNotBlank(email, "Email cannot be empty.");
        assertMatches(email, EMAIL_REGEX, "Invalid email.");

        return userRepository.save(new User(userDto));

    }

}
