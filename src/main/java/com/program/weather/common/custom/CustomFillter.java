package com.program.weather.common.custom;

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
 * The type Custom fillter.
 *
 * @author Hoapham
 */
public class CustomFillter extends GenericFilterBean {
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Create Filter
     * create a filter class when running the web will
     * check how the user name is logged in if the blocked users will logout and logout again.
     *
     * @param request  A <code>ServletRequest</code> object provides data including parameter name
     *                 and values, attributes, and an input stream.
     * @param response The charset for the MIME body response can be specified explicitly or
     *                 * implicitly. The priority order for specifying the response body is:
     * @param chain    A FilterChain is an object provided by the servlet container to the developer
     *                 * giving a view into the invocation chain of a filtered request for a resource.
     *                 * Filters use the FilterChain to invoke the next filter in the chain, or if the
     *                 * calling filter is the last filter in the chain, to invoke the resource at the
     *                 * end of the chain.
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
        //
        //verify if the user is currently logged in and get a username
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserDetails && authentication.getCredentials() == null) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            //if username is empty
            if (!username.isEmpty()) {
                UserDetails userDetailsQuery = userDetailsService.loadUserByUsername(username);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                //Obligatory user logout
                if (!userDetailsQuery.getAuthorities().containsAll(userDetails.getAuthorities())) {
                    new SecurityContextLogoutHandler().logout(req, res, authentication);
                }
            }
        }

        chain.doFilter(req, res);
    }

    /**
     * Instantiates a new Custom fillter.
     *
     * @param userDetailsService the user details service
     */
    public CustomFillter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
