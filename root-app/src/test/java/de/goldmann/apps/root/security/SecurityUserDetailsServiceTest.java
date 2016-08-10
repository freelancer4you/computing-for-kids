package de.goldmann.apps.root.security;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.goldmann.apps.root.config.InfrastructureConfig;
import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.services.UserActivityReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class })
public class SecurityUserDetailsServiceTest
{
    @Autowired
    private UserRepository             userRepository;

    private SecurityUserDetailsService service;

    @Before
    public void setUp() throws Exception
    {
        final UserActivityReport activityReport = new UserActivityReport() {

            public void registered(final User user) {
            }

            public void logout(final User user) {
            }

            public void login(final User user) {
            }
        };
        service = new SecurityUserDetailsService(userRepository, activityReport);
    }

    @Test
    public void testLoadUserByUsername()
    {
        final User user = new User(new NewUserDTO("firstName", "lastName", "userName", "email", "password", true));
        this.userRepository.save(user);
        final UserDetails result = service.loadUserByUsername("userName");
        assertNotNull(result);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNameEmpty()
    {
        service.loadUserByUsername("");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameUserNameNull()
    {
        service.loadUserByUsername(null);
    }

}
