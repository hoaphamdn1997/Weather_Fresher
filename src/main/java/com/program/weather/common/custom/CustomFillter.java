package com.program.weather.common.custom;

import com.program.weather.common.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
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
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetails = CommonUtil.getUserDetailsSecurityContext();

        if (userDetails != null) {
            try {
                UserDetails userDetailsQuery = userDetailsService.loadUserByUsername(userDetails.getUsername());

                /**
                 * if user logged has been change authority then we will logout this account
                 * check all roles and check size role had changed
                 */
                if (!userDetailsQuery.getAuthorities().containsAll(userDetails.getAuthorities())
                        || userDetails.getAuthorities().size() != userDetailsQuery.getAuthorities().size()) {
                    new SecurityContextLogoutHandler().logout(req, res, securityContext.getAuthentication());
                }
            } catch (Exception e) {
                /**
                 * if happening Exception, it mean not exists UserDetails from method loadUserByUsername,
                 * it mean that user has been deleted, so we will logout that account
                 */
                new SecurityContextLogoutHandler().logout(req, res, securityContext.getAuthentication());
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

    /**
     * Instantiates a new Custom fillter.
     */
}
