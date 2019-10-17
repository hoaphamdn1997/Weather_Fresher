package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.dto.DetailsWeatherDTO;

import java.util.List;

public interface WeatherService {
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
    List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity);

    /**
     *
     */
    List<WeatherEntity> findWeatherByUserAndDate(Long id);

    WeatherEntity getWeatherByApi(String nameCity);

    DetailsWeatherDTO foreCast(String nameCity);

    void deleteListWeatherByUser(List<WeatherEntity> listWeather);
}
