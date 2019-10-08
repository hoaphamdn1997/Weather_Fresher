package com.program.weather.common.api;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserApi {

    @Autowired
    UserServiceImpl userServiceImpl;

    /**
     * API User
     * @param user
     * @return
     */
    public UserEntity saveUser(UserEntity user) {
        return userServiceImpl.saveUser(user);
    }
}
