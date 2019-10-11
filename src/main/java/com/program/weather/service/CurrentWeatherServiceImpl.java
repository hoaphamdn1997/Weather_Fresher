package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.service.CurrentWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {

    @Autowired
    CurrentWeatherRepository weatherRepository;


    /**
     * Delete weather follow id
     *
     * @param id //id weather
     */
    @Override
    public void deleteWeather(Long id) {
        WeatherEntity weatherEntity = weatherRepository.findByWeatherId(id);
        weatherEntity.getUserEntities().removeAll(weatherEntity.getUserEntities());
        weatherRepository.delete(weatherEntity);

    }

    /**
     * List Weather User Oder by Date
     *
     * @param userEntity
     * @return List WeatherEntity
     */
    @Override
    public List<WeatherEntity> findAllByUserEntities(UserEntity userEntity) {
        return weatherRepository.findAllByUserEntitiesOrderByDateDesc(userEntity);
    }

    /**
     * List Weather User Group by Name City and Date order by Desc
     * @param id //id weather
     * @return List WeatherEntity
     */

    @Override
    public List<WeatherEntity> findWeatherByUserAndDate(Long id) {
        return weatherRepository.findDateTimeByUserGroupbyDateTimeDest(id);
    }

}
