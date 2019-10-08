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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ClientFowardController {


    @GetMapping("/processURL")
    public String processURL() {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String URL = urlMappingUser(authentication);

        return URL;
    }

    public String urlMappingUser(Authentication authentication) {
        String url = "";
        List<GrantedAuthority> authorities = getListAuthority(authentication);

        if(checkRoleUser(authorities, Constants.USER))
            url = "redirect:/home-weather";
        if(checkRoleUser(authorities, Constants.ADMIN))
            url = "redirect:/home-admin/admin";

        return url;
    }

    public boolean checkRoleUser(List<GrantedAuthority> userAuthority, String role) {
        return userAuthority.stream().anyMatch(author -> author.getAuthority().equalsIgnoreCase(role));
    }

    public List<GrantedAuthority> getListAuthority(Authentication authentication){
        List<GrantedAuthority> userAuthority = new ArrayList<GrantedAuthority>();
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        userAuthority.addAll(authorities);

        return userAuthority;

    }
}
