package com.program.weather.controller;

import com.program.weather.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Client foward controller.
 */
@Controller
public class ClientFowardController {
    /**
     * Redirect string.
     *
     * @return the string
     */
    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

    /**
     * Process url string.
     *
     * @return the string
     */
    @GetMapping("/processURL")
    public String processURL() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String URL = urlMappingUser(authentication);

        return URL;
    }

    /**
     * URL -->Role
     * <p>
     * ridirect: redirects to url mapping
     * forward: Provide forward to url with them + request (including param value)
     *
     * @param authentication
     * @return URL
     */
    private String urlMappingUser(Authentication authentication) {
        String url = "";
        List<GrantedAuthority> authorities = getListAuthority(authentication);

        if (checkRoleUser(authorities, Constants.USER))
            url = "redirect:/";
        if (checkRoleUser(authorities, Constants.ADMIN))
            url = "redirect:/home-admin/admin";
        if (checkRoleUser(authorities, Constants.GUEST))
            url = "redirect:/block";
        return url;
    }

    /**
     * Check role User
     *
     * @param userAuthority
     * @param role
     * @return Role user
     */
    private boolean checkRoleUser(List<GrantedAuthority> userAuthority, String role) {
        return userAuthority.stream().anyMatch(author -> author.getAuthority().equalsIgnoreCase(role));
    }

    /**
     * get list Author
     * abs
     *
     * @param authentication
     * @return
     */
    private List<GrantedAuthority> getListAuthority(Authentication authentication) {
        List<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        userAuthority.addAll(authorities);

        return userAuthority;

    }
}
