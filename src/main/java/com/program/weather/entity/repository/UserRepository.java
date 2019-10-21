package com.program.weather.entity.repository;

import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Use Jpa find Username in database
     *
     * @param userName
     * @return UserName in table UserEntity
     */
    UserEntity findByUserName(String userName);

    /**
     * Use jpa find id user in database
     *
     * @param id
     * @return id User in table UserEntity
     */
    UserEntity findByUserId(Long id);

    /**
     * Use jpa find email in database
     *
     * @param email
     * @return email User in table UserEntity
     */
    UserEntity findByEmail(String email);
}
