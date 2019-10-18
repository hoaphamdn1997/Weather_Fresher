package com.program.weather.common.Custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hoapham
 */
public class CustomFillter extends GenericFilterBean {
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Create Filter
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserDetails && authentication.getCredentials() == null) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            if (!username.isEmpty()) {
                UserDetails userDetailsQuery = userDetailsService.loadUserByUsername(username);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                if (!userDetailsQuery.getAuthorities().containsAll(userDetails.getAuthorities())) {
                    new SecurityContextLogoutHandler().logout(req, res, authentication);
                }
            }
        }

        chain.doFilter(req, res);
    }

    public CustomFillter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
