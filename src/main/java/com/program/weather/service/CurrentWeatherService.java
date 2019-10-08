package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;

import java.util.List;

public interface CurrentWeatherService {
	/**
	 * Save weather Service with User
	 * @param weatherEntity
	 */
	void 					   saveWeather			 (WeatherEntity weatherEntity);

	/**
	 * Update Weather With Id
	 * @param nameCity
	 */
	void            		   updateWeather(String nameCity);

	/**
	 * Delete Weather
	 * @param id
	 */
	void            		   deleteWeather(Long id);

	/**
	 * find all User
	 * @param userEntity
	 * @return
	 */
	List<WeatherEntity> findAllByUserEntities (UserEntity userEntity);

	/**
	 * get name city
	 * @param name
	 * @return
	 */
	String					   getNameCity(String name);
	/**
	 *
	 */
	List<WeatherEntity> findWeatherByUserAndDate(Long id);
}
