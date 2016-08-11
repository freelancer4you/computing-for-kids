package de.goldmann.apps.root.services;

import static de.goldmann.apps.root.test.utils.TestUtils.buildUserDto;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ComponentScan({ "de.goldmann.apps.root.services" })
@ContextConfiguration(classes = { InfrastructureConfig.class })
public class UserServiceTest {

    public static final String USERNAME = "test123";

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setup() {
        this.userService = new UserService(userRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankEmail() {
        userService.createUser(getUser("firstname", "lastname", "test456", "", "Password3", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankPassword() {
        userService.createUser(getUser("firstname", "lastname", "test456", "test@gmail.com", "", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankUser() {
        userService.createUser(getUser("firstname", "lastname", "", "test@gmail.com", "Password3", "phone"));
    }

    @Test
    public void testCreateValidUser() {
        userService.createUser(getUser("firstname", "lastname", "test456", "test@gmail.com", "Password3", "phone"));
        final User user = userService.findUserByUsername("test456");

        assertTrue("username not expected " + user.getUsername(), "test456".equals(user.getUsername()));
        assertTrue("email not expected " + user.getEmail(), "test@gmail.com".equals(user.getEmail()));

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertTrue("password not expected " + user.getPasswordDigest(),
                passwordEncoder.matches("Password3", user.getPasswordDigest()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        userService.createUser(getUser("firstname", "lastname", "username", "test", "Password3", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordPolicy() {
        userService.createUser(getUser("firstname", "lastname", "username", "test@gmail.com", "Password", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameLength() {
        userService.createUser(getUser("firstname", "lastname", "test", "test@gmail.com", "Password3", "phone"));
    }

    @Test
    public void testUserNotFound() {
        final User user = userService.findUserByUsername("doesnotexist");
        assertNull("User must be null", user);
    }

    private NewUserDTO getUser(final String firstName, final String lastName, final String userName, final String email,
            final String password, final String phoneNumber) {
        return buildUserDto(firstName, lastName, userName, email, password, phoneNumber);
    }

}
