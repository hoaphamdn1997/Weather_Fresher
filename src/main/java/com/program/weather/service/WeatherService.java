package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.dto.DetailsWeatherDTO;

import java.util.List;

public interface WeatherService {
    /**
     * Delete Weather by id
     *
     * @param id
     */
    void deleteWeather(Long id);

    /**
     * List Weather Folow User  order by date descending order
     *
     * @param userEntity
     * @return List WeatherEntity desc
     */
    List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity);

    /**
     * List Weather Folow User group by nameCity  order by date descending order
     *
     * @param id//id user
     * @return List WeatherEntity date desc and group by name city
     */
    List<WeatherEntity> findWeatherByUserAndDate(Long id);

    /**
     * Get Weather Api Follow name city
     *
     * @param nameCity
     * @return WeatherEntity
     */
    WeatherEntity getWeatherByApi(String nameCity);

    /**
     * Details Weather ForeCast 5 day
     *
     * @param nameCity
     * @return DetailsWeatherDTO
     */
    DetailsWeatherDTO foreCast(String nameCity);

    /**
     * Delete All Weather Folow user
     *
     * @param listWeather
     */
    void deleteListWeatherByUser(List<WeatherEntity> listWeather);
}
