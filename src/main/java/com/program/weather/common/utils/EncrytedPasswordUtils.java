package com.program.weather.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The type Encryted password utils.
 */
public class EncrytedPasswordUtils {

    /**
     * Encryte password string.
     *
     * @param password the password
     * @return the string
     */
// Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
