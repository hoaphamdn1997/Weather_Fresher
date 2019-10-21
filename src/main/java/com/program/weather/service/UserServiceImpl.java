package com.program.weather.service;


import com.program.weather.common.utils.Constants;
import com.program.weather.common.utils.EncrytedPasswordUtils;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.entity.repository.RoleRepository;
import com.program.weather.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    WeatherService weatherService;

    @Autowired
    CurrentWeatherRepository weatherRepository;

    /**
     * Find user by User Name
     *
     * @param userName
     * @return userName
     */
    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Create user
     *
     * @param user
     * @return Save user
     */
    @Override
    public UserEntity saveUser(UserEntity user) {
        //set PassWord
        user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(user.getEncrytedPassword()));
        //set Status True
        user.setEnabled(true);
        //set date = today
        user.setCreateDate(new Timestamp(System.currentTimeMillis()));
        //set role user for account
        RoleEntity userrole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<RoleEntity>(Arrays.asList(userrole)));
        return userRepository.save(user);
    }

    /**
     * Delete User by Id
     *
     * @param id
     */
    @Override
    public void delete(Long id) {

        UserEntity userEntity = userRepository.findByUserId(id);
        userEntity.getRoles().removeAll(userEntity.getRoles());

        List<WeatherEntity> listWeatherByUser = weatherRepository.findAllByUserEntities(userEntity);
        weatherService.deleteListWeatherByUser(listWeatherByUser);

        userRepository.delete(userEntity);
    }

    /**
     * List All User
     *
     * @return all user
     */
    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


    /**
     * Edit Status User
     *
     * @param id
     */
    @Override
    public void editActiveUser(Long id) {
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity.isEnabled()) {

            userEntity.setEnabled(Constants.UN_ACTIVE);
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleRepository.findByRole("ROLE_GUEST"))));
        } else {
            userEntity.setEnabled(Constants.ACTIVE);
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleRepository.findByRole("ROLE_USER"))));

        }
        userRepository.save(userEntity);
    }

    /**
     * Edit Role User
     *
     * @param id
     * @param role
     */
    @Override
    public void editRoleUser(Long id, String role) {
        UserEntity userEntity = userRepository.findByUserId(id);
        RoleEntity roleEntity = roleRepository.findByRole(role);
        userEntity.isEnabled();
        if (role.equalsIgnoreCase(Constants.GUEST)) {
            userEntity.setEnabled(Constants.UN_ACTIVE);
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleRepository.findByRole("ROLE_GUEST"))));
        } else {
            userEntity.setEnabled(Constants.ACTIVE);
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntity)));
        }

        userRepository.save(userEntity);
    }

    /**
     * find User Id
     *
     * @param id
     * @return
     */

    @Override
    public UserEntity findByUserId(Long id) {

        return userRepository.findByUserId(id);
    }

    /**
     * find User by Email
     *
     * @param email
     * @return
     */
    @Override
    public UserEntity findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
