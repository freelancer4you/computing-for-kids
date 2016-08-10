package de.goldmann.apps.root.services;

import static de.goldmann.apps.root.services.ValidationUtils.assertMatches;
import static de.goldmann.apps.root.services.ValidationUtils.assertMinimumLength;
import static de.goldmann.apps.root.services.ValidationUtils.assertNotBlank;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.model.User;

@Service
public class UserService
{
    private static final Logger  LOGGER         = LogManager.getLogger(UserService.class);

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

    private static final Pattern EMAIL_REGEX    = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final UserRepository       userRepository;

    @Autowired
    public UserService(final UserRepository userRepository)
    {
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
    public User createUser(final NewUserDTO userDto)
    {
        final String username = userDto.getUserName();
        final String email = userDto.getEmail();
        final String password = userDto.getPassword();

        assertNotBlank(username, "Username cannot be empty.");
        assertMinimumLength(username, 6, "Username must have at least 6 characters.");
        assertNotBlank(email, "Email cannot be empty.");
        assertMatches(email, EMAIL_REGEX, "Invalid email.");
        assertNotBlank(password, "Password cannot be empty.");
        assertMatches(password, PASSWORD_REGEX,
                "Password must have at least 6 characters, with 1 numeric and 1 uppercase character.");

        // if (!userRepository.isUsernameAvailable(username))
        // {
        // throw new IllegalArgumentException("The username is not available.");
        // }

        return userRepository.save(new User(userDto));

    }

    @Transactional(readOnly = true)
    public User findUserByUsername(final String username)
    {
        return userRepository.findUserByUsername(username);
    }

}
