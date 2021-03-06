package de.goldmann.apps.root.security;

import static de.goldmann.apps.root.test.utils.TestUtils.buildUserDto;
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
import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.root.dto.DefaultAccountDTO;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.UserActivityReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { InfrastructureConfig.class })
public class SecurityUserDetailsServiceTest {
    @Autowired
    private DefaultAccountRepository userRepository;

    private SecurityUserDetailsService service;

    @Before
    public void setUp() throws Exception {
        final UserActivityReport activityReport = new UserActivityReport() {


            @Override
            public void logout(final UserId user) {
            }

            @Override
            public void login(final UserId user) {
            }

            @Override
            public void registered(final UserId user, final Course course) {
            }
        };
        service = new SecurityUserDetailsService(userRepository, activityReport);
    }

    @Test
    public void testLoadUserByUsername() {
        final DefaultAccountDTO dto = buildUserDto();
        final DefaultAccount user = new DefaultAccount(dto);
        userRepository.save(user);
        final UserDetails result = service.loadUserByUsername(dto.getEmail());
        assertNotNull(result);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNameEmpty() {
        service.loadUserByUsername("");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameUserNameNull() {
        service.loadUserByUsername(null);
    }

}
