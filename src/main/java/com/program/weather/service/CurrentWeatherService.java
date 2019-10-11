package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;

import java.util.List;

public interface CurrentWeatherService {
    /**
     * Delete Weather
     *
     * @param id
     */
    void deleteWeather(Long id);

    /**
     * find all User
     *
     * @param userEntity
     * @return
     */
    List<WeatherEntity> findAllByUserEntities(UserEntity userEntity);

    /**
     *
     */
    List<WeatherEntity> findWeatherByUserAndDate(Long id);
}
