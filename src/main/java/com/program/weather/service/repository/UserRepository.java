package com.program.weather.service.repository;

import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Use Jpa find Username in database
     *
     * @param userName the user name
     * @return UserName in table UserEntity
     */
    UserEntity findByUserName(String userName);

    /**
     * Use jpa find id user in database
     *
     * @param id the id
     * @return id User in table UserEntity
     */
    UserEntity findByUserId(Long id);

    /**
     * Use jpa find email in database
     *
     * @param email the email
     * @return email User in table UserEntity
     */
    UserEntity findByEmail(String email);
}
