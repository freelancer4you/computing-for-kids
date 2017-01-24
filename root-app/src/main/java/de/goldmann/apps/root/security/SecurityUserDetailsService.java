package de.goldmann.apps.root.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.services.UserActivityReport;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(SecurityUserDetailsService.class);

    private final DefaultAccountRepository userRepository;

    private final UserActivityReport activityReport;

    @Autowired
    public SecurityUserDetailsService(final DefaultAccountRepository userRepository,
            @Lazy final UserActivityReport activityReport) {
        this.userRepository = Objects.requireNonNull(userRepository,
                "Parameter 'userRepository'  darf nicht null sein.");

        this.activityReport = activityReport;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final DefaultAccount user = userRepository.findByEmail(email);

        if (user == null) {
            final String message = "Email '" + email + "' not found!!!";
            LOGGER.info(message);
            throw new UsernameNotFoundException(message);
        }
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        if (activityReport != null) {
            this.activityReport.login(user);
        }

        return new org.springframework.security.core.userdetails.User(email, user.getPasswordDigest(), authorities);
    }
}
