package com.program.weather.common.Custom;

import java.util.HashSet;
import java.util.Set;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.program.weather.entity.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load user Database with username
     *
     * @param userName
     * @return return one userdetails have Username ,password ,authorities
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Use JPA find  User name
        UserEntity user = userRepository.findByUserName(userName);
        //If User Not found => User not found Exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        //get roles for user
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<RoleEntity> roles = user.getRoles();
        for (RoleEntity role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        //return one userdetails have Username ,password ,authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getEncrytedPassword(), grantedAuthorities);
    }

}
