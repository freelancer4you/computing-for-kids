package de.goldmann.map.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

import de.goldmann.apps.root.config.CsrfHeaderFilter;
import de.goldmann.apps.root.security.AjaxAuthenticationSuccessHandler;
import de.goldmann.apps.root.services.VisitorsCounter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private static final Logger LOGGER = LogManager.getLogger(SecurityConfiguration.class);

    @Autowired
    private VisitorsCounter visitorsCounter;
    
    @Autowired
    private UserDetailsService  userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        final CsrfHeaderFilter csrfTokenFilter = new CsrfHeaderFilter(visitorsCounter);
        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class).csrf()
                .csrfTokenRepository(csrfTokenRepository());

        http.authorizeRequests()
                .antMatchers("/app/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/js/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/index.html", "/header.html", "/modalLogin.html", "/", "/modalSignup.html",
                        "/partials/index.html")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/user")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(
                        new AjaxAuthenticationSuccessHandler(
                                new SavedRequestAwareAuthenticationSuccessHandler()))
                .and().httpBasic().and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();

        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        
        http
        .sessionManagement()
        .maximumSessions(1)
        // TODO this is not mapped yet
        .expiredUrl("/login?expired")
        .maxSessionsPreventsLogin(true)
        .and()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .invalidSessionUrl("/");

    }

    private CsrfTokenRepository csrfTokenRepository()
    {
        final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    static class CustomAccessDeniedHandler implements AccessDeniedHandler
    {
        public void handle(HttpServletRequest request, HttpServletResponse response,
                AccessDeniedException accessDeniedException) throws IOException, ServletException
        {
            LOGGER.warn("Arrived in custom access denied handler.");
            HttpSession session = request.getSession();
            LOGGER.info("Session is " + session);
            LOGGER.info("Session id = " + session.getId());
            LOGGER.info("Session max interval=" + session.getMaxInactiveInterval());
            LOGGER.info("Session last used=" + session.getLastAccessedTime());
            LOGGER.info("Time now=" + new Date().getTime());

            LOGGER.info("csrf:");
            Object csrf = request.getAttribute("_csrf");

            if (csrf == null)
            {
                LOGGER.info("csrf is null");
            }
            else
            {
                LOGGER.info(csrf.toString());
                if (csrf instanceof DefaultCsrfToken)
                {
                    DefaultCsrfToken token = (DefaultCsrfToken) csrf;
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
