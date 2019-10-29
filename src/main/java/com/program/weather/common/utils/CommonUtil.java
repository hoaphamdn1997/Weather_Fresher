package com.program.weather.common.utils;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * The type Common util.
 */
public class CommonUtil {

    /**
     * Format TimeStamp to String yyyymmdd
     *
     * @param ts //TIMESTAMP
     * @return formattedDate //yyyyMMdd
     */
    public static String formatToString(Timestamp ts) {
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }

    /**
     * Parse curTime to String
     *
     * @return formattedDate //yyyyMMdd
     */
    public static String curTimeToString() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }

    /**
     * get UserDetails from Authentication of SecurityContextHolder
     * if authentication.getPrincipal() is UserDetails object then return UserDetails
     * else then return null, or Authentication of SecurityContextHolder was been null then return null
     *
     * @return the user details security context
     */
    public static UserDetails getUserDetailsSecurityContext() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser;
                    } else
                        return null;
                }).orElse(null);

    }
}
