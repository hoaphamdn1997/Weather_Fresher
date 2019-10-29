package com.program.weather.common.security;

import com.program.weather.common.custom.CustomFillter;
import com.program.weather.common.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.program.weather.common.custom.CustomUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * The type Web security config.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Configure global.
     *
     * @param auth the auth
     * @throws Exception the exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Set sevice find user in Database
        // Set password follow PasswordEncoder.
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterAt(new CustomFillter(customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //Optional login
                .antMatchers("/login", "/logout", "/registration", "/forgot-password**", "/reset-password**").permitAll()
                //Only Role_User and Role Admin
                .antMatchers("/home-weather/**", "/", "/home").hasAnyAuthority(Constants.USER,Constants.ADMIN)
                .antMatchers("/block").hasAuthority(Constants.GUEST)
                //Only admin
                .antMatchers("/home-admin/admin").hasAuthority(Constants.ADMIN)
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()
                //go to login page
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginAction")
                //this is parameter in login page
                .passwordParameter("encrytedPassword")
                .usernameParameter("username")
                //login Done -> controller
                .defaultSuccessUrl("/processURL")
                //login faile -> massage error -> login page
                .failureUrl("/login?error=error")
                .and()
                .logout()
                //logout -> login page
                .logoutSuccessUrl("/login")
                .and()
                //if account access denied -> 403 page
                .csrf()
                .disable().exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
