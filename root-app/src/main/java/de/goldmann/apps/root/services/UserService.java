package de.goldmann.apps.root.services;

import static de.goldmann.apps.root.services.ValidationUtils.assertMatches;
import static de.goldmann.apps.root.services.ValidationUtils.assertNotBlank;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.goldmann.apps.root.dao.GoogleAccountRepository;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.GoogleAccountDTO;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.GoogleAccount;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.model.UserId;

@Service
public class UserService {

    private static final Pattern EMAIL_REGEX = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private final UserRepository userRepository;

    private final GoogleAccountRepository googleAccountRepository;

    @Autowired
    public UserService(final UserRepository userRepository, final GoogleAccountRepository googleAccountRepository) {
        this.userRepository = Objects.requireNonNull(userRepository,
                "Parameter 'userRepository' darf nicht null sein.");
        this.googleAccountRepository = Objects
                .requireNonNull(googleAccountRepository, "Parameter 'googleAccountRepository' darf nicht null sein.");
    }

    /**
     * Prueft, anhand der Email, ob ein Benutzer bereits existiert.
     *
     * @param email
     * @return
     */
    public boolean userExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    /**
     * stores a new {@link User} in the database
     *
     * @return {@link User}
     *
     */
    @Transactional
    public UserId createUser(final UserDTO userDto) {
        final String email = userDto.getEmail();
        assertNotBlank(email, "Email cannot be empty.");
        assertMatches(email, EMAIL_REGEX, "Invalid email.");

        return userRepository.save(new User(userDto));

    }

    /**
     * Prueft, anhand der Email, ob der Account bereits existiert.
     *
     * @param email
     * @return
     */
    public boolean googleAccountExists(final String email) {
        return googleAccountRepository.findByEmail(email) != null;
    }

    /**
     * stores a new {@link GoogleAccount} in the database
     *
     * @return {@link GoogleAccount}
     *
     */
    @Transactional
    public GoogleAccount createAcc(final GoogleAccountDTO acc) {
        return googleAccountRepository.save(new GoogleAccount(acc));
    }

}
