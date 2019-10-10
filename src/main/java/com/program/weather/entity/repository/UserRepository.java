package com.program.weather.entity.repository;

import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //Find user name
	UserEntity findByUserName(String userName);
	//Find User id
    UserEntity findByUserId(Long id);
    //Find User email
    UserEntity findByEmail(String email);
}
