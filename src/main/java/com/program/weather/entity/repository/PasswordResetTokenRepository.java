package com.program.weather.entity.repository;

import com.program.weather.entity.PasswordResetToken;
import com.program.weather.entity.UserEntity;
import com.program.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
