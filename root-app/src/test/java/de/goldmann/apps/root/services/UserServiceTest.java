package de.goldmann.apps.root.services;

import static de.goldmann.apps.root.test.utils.TestUtils.buildUserDto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.UserDTO;

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
        userService.createUser(getUser("firstname", "lastname", "", "Password3", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankPassword() {
        userService.createUser(getUser("firstname", "lastname", "test@gmail.com", "", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankUser() {
        userService.createUser(getUser("firstname", "lastname", "test@gmail.com", "Password3", "phone"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmail() {
        userService.createUser(getUser("firstname", "lastname", "test", "Password3", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordPolicy() {
        userService.createUser(getUser("firstname", "lastname", "test@gmail.com", "Password", "phone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsernameLength() {
        userService.createUser(getUser("firstname", "lastname", "test@gmail.com", "Password3", "phone"));
    }

    private UserDTO getUser(final String firstName, final String lastName, final String email, final String password,
            final String phoneNumber) {
        return buildUserDto("herr", null, firstName, lastName, email, password, phoneNumber);
    }

}
