package com.program.weather.common.api;



import com.program.weather.common.utils.Constants;
import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.dto.DetailsWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.CurrentWeatherService;
import com.program.weather.service.converter.WeatherConverter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WeatherApi {

    @Autowired
    CurrentWeatherService weatherService;

    @Autowired
    WeatherConverter weatherConverter;

    /**
     * Delete Weather by id
     * @param id
     */
    public void deleteWeather(Long id) {
        weatherService.deleteWeather(id);
    }

    /**
     * List Weather by user
     * @param userEntity
     * @return
     */
    public List<WeatherEntity> findAllByUserEntities(UserEntity userEntity) {
        return weatherService.findAllByUserEntities(userEntity);
    }

    /**
     *
     * @param id
     * @return
     */
    public List<WeatherEntity> findWeatherUserandDate(long id){
        return weatherService.findWeatherByUserAndDate(id);
}
    /**
     * Rest Data Json Data With NameCity And Convert to EntiTy
     * @param name
     * @return
     */
    public WeatherEntity restJsonData(String name) {
        String URL = Constants.WEATHER_URL + name + Constants.APPID;
        RestTemplate restTemplate = new RestTemplate();
        CurrentWeatherDTO weatherDTO = restTemplate.getForObject(URL, CurrentWeatherDTO.class);
        return weatherConverter.convertToEntity(weatherDTO);
    }

    /**
     * Detail Weather With City
     * @param name
     * @return
     */
    public DetailsWeatherDTO foreCast(String name) {
        String URL = Constants.FORECAST_URL + name + Constants.APPID;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL, DetailsWeatherDTO.class);
    }

}
