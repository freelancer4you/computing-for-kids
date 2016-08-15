package de.goldmann.apps.root.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import de.goldmann.apps.root.services.VisitorsCounter;

public class CsrfHeaderFilter extends OncePerRequestFilter {
    private static final String XSRF_TOKEN = "XSRF-TOKEN";
    private VisitorsCounter visitorsCounter;

    public CsrfHeaderFilter() {
    }

    public CsrfHeaderFilter(final VisitorsCounter visitorsCounter) {
        this.visitorsCounter = visitorsCounter;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        final CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(request, XSRF_TOKEN);

            final String token = csrf.getToken();

            if (cookie == null && visitorsCounter != null) {
                visitorsCounter.count();
            }

            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(XSRF_TOKEN, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        filterChain.doFilter(request, response);
    }
}