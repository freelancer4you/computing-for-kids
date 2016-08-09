package de.goldmann.apps.root.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.model.User;

@Service
public class SecurityUserDetailsService implements UserDetailsService
{
    private static final Logger LOGGER = LogManager.getLogger(SecurityUserDetailsService.class);

    private UserRepository      userRepository;

    @Autowired
    public SecurityUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = Objects.requireNonNull(userRepository,
                "Parameter 'userRepository'  darf nicht null sein.");
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        final User user = userRepository.findUserByUsername(username);

        if (user == null)
        {
            final String message = "Username '" + username + "' not found!!!";
            LOGGER.info(message);
            throw new UsernameNotFoundException(message);
        }
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(username, user.getPasswordDigest(),
                authorities);
    }
}
