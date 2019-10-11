package com.program.weather.entity.repository;

import com.program.weather.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserName(String userName);
    UserEntity findByUserId(Long id);
    UserEntity findByEmail(String email);
   
}
