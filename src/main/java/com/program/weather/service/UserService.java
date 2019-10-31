package com.program.weather.service;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.dto.UserDTO;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * find user by user name
     *
     * @param userName the user name
     * @return user entity
     */
    UserEntity findByUserName(String userName);

    /**
     * Save User
     *
     * @param userEntity the user entity
     * @return user entity
     */
    UserEntity saveUser(UserEntity userEntity);

    /**
     * find User by id
     *
     * @param id the id
     * @return user entity
     */
    UserEntity findByUserId(Long id);

    /**
     * find User by email
     *
     * @param email the email
     * @return user entity
     */
    UserEntity findByUserEmail(String email);

    /**
     * edit Status User
     *
     * @param id the id
     */
    void editActiveUser(Long id );

    /**
     * edit role User
     *
     * @param id   the id
     * @param role the role
     */
    void editRoleUser(Long id, String role);

    /**
     * Delete User by id
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * find all user
     *
     * @return list list
     */
    List<UserEntity> findAll();

    /**
     * update password
     *
     * @param password the password
     * @param userId   the user id
     */
    void updatePassword(String password, Long userId);

}
