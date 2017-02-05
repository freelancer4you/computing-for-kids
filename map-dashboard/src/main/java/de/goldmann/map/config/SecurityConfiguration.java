package de.goldmann.map.config;


import static de.goldmann.apps.root.UIConstants.COURSES_DETAILS_REQUEST_PATH;
import static de.goldmann.apps.root.UIConstants.COURSES_REQUEST_PATH;
import static de.goldmann.apps.root.UIConstants.DEFAULT_REGISTRATION;
import static de.goldmann.apps.root.UIConstants.GOOGLE_REGISTRATION;
import static de.goldmann.apps.root.UIConstants.USER_PATH;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import de.goldmann.apps.root.security.AjaxAuthenticationSuccessHandler;
import de.goldmann.apps.root.security.CsrfHeaderFilter;
import de.goldmann.apps.root.services.VisitorsCounter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(SecurityConfiguration.class);

    @Autowired
    @Lazy
    private VisitorsCounter visitorsCounter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        final CsrfHeaderFilter csrfTokenFilter = new CsrfHeaderFilter(visitorsCounter);
        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());

        // @formatter:off
        http.authorizeRequests()
        // Nur ohne Login erlaubte URLS
        .antMatchers("/app/**").permitAll()
        .antMatchers("/img/**").permitAll()
        // TODO nicht alle JS-Skripte sollten erlaubt sein
        .antMatchers("/js/**").permitAll()
        .antMatchers("/fonts/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/partials/about/index.html").permitAll()
        .antMatchers("/partials/contact/index.html").permitAll()
        .antMatchers("/partials/courses/index.html").permitAll()
        .antMatchers("/partials/courses/kids/index.html").permitAll()
        .antMatchers("/partials/courses/teachers/index.html").permitAll()
        .antMatchers("/partials/courses/register/index.html").permitAll()
        .antMatchers("/partials/courses/register/success.html").permitAll()
        .antMatchers("/partials/impressum/index.html").permitAll()
        .antMatchers("/partials/courses/details/**").permitAll()
        .antMatchers("/partials/courses/register/modalAgb.htm").permitAll()
        .antMatchers("/partials/courses/register/modalDisclaimer.htm").permitAll()
        .antMatchers("/index.html", "/modalLogin.htm", "/",  "/home",
                "/partials/index.html").permitAll()
        .antMatchers(HttpMethod.POST, USER_PATH).permitAll()
        .antMatchers(HttpMethod.POST, DEFAULT_REGISTRATION).permitAll()
        .antMatchers(HttpMethod.POST, GOOGLE_REGISTRATION).permitAll()
        .antMatchers(HttpMethod.GET, COURSES_REQUEST_PATH).permitAll()
        .antMatchers(HttpMethod.GET, COURSES_DETAILS_REQUEST_PATH).permitAll()

        .anyRequest().authenticated().and()

        .formLogin()
        .defaultSuccessUrl("/")
        .loginProcessingUrl("/authenticate")
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(
                new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
        .and()
        .httpBasic()
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();

        final CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.sessionManagement()
        .maximumSessions(1)
        // TODO this is not mapped yet
        .expiredUrl("/login?expired")
        // TODO should this be set to true, if yes then LoginTest will fail
        .maxSessionsPreventsLogin(false)
        .and()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/");
        // @formatter:on

    }

    private CsrfTokenRepository csrfTokenRepository() {
        final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    static class CustomAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(final HttpServletRequest request, final HttpServletResponse response,
                final AccessDeniedException accessDeniedException) throws IOException {
            LOGGER.warn("Arrived in custom access denied handler.");
            final HttpSession session = request.getSession();
            LOGGER.info("Session is " + session);
            LOGGER.info("Session id = " + session.getId());
            LOGGER.info("Session max interval=" + session.getMaxInactiveInterval());
            LOGGER.info("Session last used=" + session.getLastAccessedTime());
            LOGGER.info("Time now=" + new Date().getTime());

            LOGGER.info("csrf:");
            final Object csrf = request.getAttribute("_csrf");

            if (csrf == null) {
                LOGGER.info("csrf is null");
            }
            else {
                LOGGER.info(csrf.toString());
                if (csrf instanceof DefaultCsrfToken) {
                    final DefaultCsrfToken token = (DefaultCsrfToken) csrf;
                    LOGGER.info("Parm name " + token.getParameterName());
                    LOGGER.info("Token " + token.getToken());
                }

            }
            LOGGER.info("Request:");
            LOGGER.info(request.toString());
            LOGGER.info("Response:");
            LOGGER.info(response.toString());
            LOGGER.info("Exception:");
            LOGGER.info(accessDeniedException.toString());
        }
    }
}
