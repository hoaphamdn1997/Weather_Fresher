package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.dto.DetailsWeatherDTO;

import java.util.List;

/**
 * The interface Weather service.
 */
public interface WeatherService {
    /**
     * Delete Weather by id
     *
     * @param id the id
     */
    void deleteWeather(Long id);

    /**
     * List Weather Folow User  order by date descending order
     *
     * @param userEntity the user entity
     * @return List WeatherEntity desc
     */
    List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity);

    /**
     * List Weather Folow User group by nameCity  order by date descending order
     *
     * @param id //id user
     * @return List WeatherEntity date desc and group by name city
     */
    List<WeatherEntity> findWeatherByUserAndDate(Long id);

    /**
     * Get Weather Api Follow name city
     *
     * @param nameCity the name city
     * @return WeatherEntity weather by api
     */
    WeatherEntity getWeatherByApi(String nameCity);

    /**
     * Details Weather ForeCast 5 day
     *
     * @param nameCity the name city
     * @return DetailsWeatherDTO details weather dto
     */
    DetailsWeatherDTO foreCast(String nameCity);

    /**
     * Delete All Weather Folow user
     *
     * @param listWeather the list weather
     */
    void deleteListWeatherByUser(List<WeatherEntity> listWeather);
}
