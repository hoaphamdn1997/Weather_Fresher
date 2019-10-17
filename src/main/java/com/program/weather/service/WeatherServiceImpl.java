package com.program.weather.service;

import com.program.weather.entity.WeatherEntity;

import com.program.weather.entity.UserEntity;
import com.program.weather.entity.repository.CurrentWeatherRepository;
import com.program.weather.service.converter.WeatherConverter;
import com.program.weather.service.dto.CurrentWeatherDTO;
import com.program.weather.service.dto.DetailsWeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    CurrentWeatherRepository weatherRepository;
    @Autowired
    WeatherConverter weatherConverter;

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
     * @param id //id weather
     * @return List WeatherEntity
     */

    @Override
    public List<WeatherEntity> findWeatherByUserAndDate(Long id) {
        return weatherRepository.findDateTimeByUserGroupbyDateTimeDest(id);
    }
    /**
     * URL get forecast weather by name city
     * @param nameCity
     * @return
     */
    private String urlGetForecast(String nameCity) {

        return weatherURL+weatherVersion+weatherForecast+"q="+nameCity+"&APPID="+appID+"&units=imperial";
    }
    /**
     * URL get current weather by API
     * @param nameCity
     * @return
     */
    private String urlGetWeather(String nameCity) {

        return weatherURL+weatherVersion+weatherCurrent+"q="+nameCity+"&APPID="+appID+"&units=imperial";
    }
    public WeatherEntity getWeatherByApi(String nameCity) {

        String URL = urlGetWeather(nameCity);
        RestTemplate restTemplate = new RestTemplate();
        CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);

        return weatherConverter.convertToEntity(weatherDTO);
    }
    /**
     * Call API Forecast Weather By Name City
     *
     * @param nameCity
     * @return DetailWeatherDTO
     */
    public DetailsWeatherDTO foreCast(String nameCity) {

        String URL = urlGetForecast(nameCity);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URL, DetailsWeatherDTO.class);
    }

    @Override
    public void deleteListWeatherByUser(List<WeatherEntity> listWeather) {
        weatherRepository.deleteAll(listWeather);
    }
}
