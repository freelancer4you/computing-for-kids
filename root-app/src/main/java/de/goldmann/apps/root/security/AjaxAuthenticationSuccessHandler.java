package de.goldmann.apps.root.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationSuccessHandler defaultHandler;

    public AjaxAuthenticationSuccessHandler(final AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {
        if ("true".equals(request.getHeader("X-Login-Ajax-call"))) {
            final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            final StringBuilder roles = new StringBuilder();
            for (final GrantedAuthority grantedAuthority : authorities) {
                roles.append(grantedAuthority.getAuthority());
            }
            // response.getWriter().print("ok");
            response.getWriter().print(roles.toString());
            response.getWriter().flush();
        }
        else {
            defaultHandler.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
