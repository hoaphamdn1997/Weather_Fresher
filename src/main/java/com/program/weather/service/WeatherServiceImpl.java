package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;

import com.program.weather.entity.UserEntity;
import com.program.weather.service.repository.CurrentWeatherRepository;
import com.program.weather.service.mapper.WeatherConverter;
import com.program.weather.service.dto.CurrentWeatherDTO;
import com.program.weather.service.dto.DetailsWeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Weather service.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    /**
     * The Weather repository.
     */
    @Autowired
    CurrentWeatherRepository weatherRepository;

    /**
     * The Weather converter.
     */
    @Autowired
    WeatherConverter weatherConverter;

    /**
     * The Rest template.
     */
    @Autowired
    RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String appID;

    @Value("${weather.url}")
    private String weatherURL;

    @Value("${weather.url.version}")
    private String weatherVersion;

    @Value("${weather.url.current}")
    private String weatherCurrent;

    @Value("${weather.url.forecast}")
    private String weatherForecast;

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
    public List<WeatherEntity> findAllByUserEntitiesOrderByDateDesc(UserEntity userEntity) {
        return weatherRepository.findAllByUserEntitiesOrderByDateDesc(userEntity);
    }

    /**
     * List Weather User Group by Name City and Date order by Desc
     *
     * @param id //id User
     * @return List WeatherEntity
     */

    @Override
    public List<WeatherEntity> findWeatherByUserAndDate(Long id) {
        return weatherRepository.findDateTimeByUserGroupbyDateTimeDest(id);
    }

    /**
     * URL get forecast weather by name city
     *
     * @param nameCity
     * @return
     */
    private String urlApiGetForecast(String nameCity) {

        return weatherURL + weatherVersion + weatherForecast + "q=" + nameCity + "&APPID=" + appID + "&units=imperial";
    }

    /**
     * URL get current weather by API
     *
     * @param nameCity
     * @return
     */
    private String urlApiGetWeather(String nameCity) {

        return weatherURL + weatherVersion + weatherCurrent + "q=" + nameCity + "&APPID=" + appID + "&units=imperial";
    }

    /**
     * Search weather by Name City
     *
     * @param nameCity
     * @return
     */
    public WeatherEntity getWeatherByApi(String nameCity) {
        CurrentWeatherDTO weatherDTO = restTemplate.getForObject(urlApiGetWeather(nameCity).toString(), CurrentWeatherDTO.class);
        //convert weatherDTO to weatherEntity
        return weatherConverter.convertToEntity(weatherDTO);
    }

    /**
     * Call API Forecast Weather By Name City
     *
     * @param nameCity
     * @return DetailWeatherDTO
     */
    public DetailsWeatherDTO foreCast(String nameCity) {

        return restTemplate.getForObject(urlApiGetForecast(nameCity).toString(), DetailsWeatherDTO.class);
    }

    /**
     * Delete All Weather Folow user
     *
     * @param listWeather
     */
    @Override
    public void deleteListWeatherByUser(List<WeatherEntity> listWeather) {
        weatherRepository.deleteAll(listWeather);
    }
}
