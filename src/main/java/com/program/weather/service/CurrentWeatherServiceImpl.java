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
     * Save Weather
     *
     * @param weatherEntity
     */
    @Override
    public void saveWeather(WeatherEntity weatherEntity) {

        weatherRepository.save(weatherEntity);

    }

    /**
     * update weather
     *
     * @param nameCity
     */
    @Override
    public void updateWeather(String nameCity) {
    }

    /**
     * Delete weather
     *
     * @param id
     */
    @Override
    public void deleteWeather(Long id) {
        WeatherEntity weatherEntity = weatherRepository.findByWeatherId(id);
        weatherEntity.getUserEntities().removeAll(weatherEntity.getUserEntities());
        weatherRepository.delete(weatherEntity);

    }

    /**
     * Find ALl Weather With user
     *
     * @param userEntity
     * @return
     */
    @Override
    public List<WeatherEntity> findAllByUserEntities(UserEntity userEntity) {
        return weatherRepository.findAllByUserEntitiesOrderByDateDesc(userEntity);
    }

    /**
     * List weather follow user follow date dest
     * @param id
     * @return
     */
    @Override
    public List<WeatherEntity> findWeatherByUserAndDate(Long id) {
        return weatherRepository.findDateTimeByUserGroupbyDateTimeDest(id);
    }

}
