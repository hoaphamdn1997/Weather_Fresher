package com.program.weather.service;

import com.program.weather.entity.UserEntity;

import java.util.List;

public interface UserService {
    /**
     * find user by user name
     *
     * @param userName
     * @return
     */
    UserEntity findByUserName(String userName);

    /**
     * Save User
     *
     * @param userEntity
     * @return
     */
    UserEntity saveUser(UserEntity userEntity);

    /**
     * find User by id
     * @param id
     * @return
     */
    UserEntity findByUserId(Long id);

    /**
     * find User by email
     * @param email
     * @return
     */

    UserEntity findByUserEmail(String email);

    /**
     * edit Status User
     * @param id
     */

    void editActiveUser(Long id );

    /**
     * edit role User
     * @param id
     * @param role
     */

    void editRoleUser(Long id, String role);

    /**
     * Delete User by id
     * @param id
     */

    void delete(Long id);

    /**
     * find all user
     * @return
     */

    List<UserEntity> findAll();

    /**
     * update password
     * @param password
     * @param userId
     */
    void updatePassword(String password, Long userId);
}
