package com.program.weather.common.security;

import com.program.weather.common.Custom.CustomFillter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.program.weather.common.Custom.CustomUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Set sevice find user in Database
        // Set password follow PasswordEncoder.
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAt(new CustomFillter(customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //Optional login
                .antMatchers("/login", "/logout", "/registration", "/forgot-password**", "/reset-password**")
                //Only Role_User and Role Admin
                .permitAll().antMatchers("/home-weather/**", "/", "/home")
                .hasAnyRole("USER", "ADMIN")
                //Only admin
                .antMatchers("/home-admin/admin")
                .hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()
                //go to login page
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginA")
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
                .csrf().disable().exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
