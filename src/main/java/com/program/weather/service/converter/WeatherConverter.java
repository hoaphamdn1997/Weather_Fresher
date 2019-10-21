package com.program.weather.service.converter;

import com.program.weather.service.dto.CurrentWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class WeatherConverter {
    /**
     * Conver WeatherDTO --> WeatherEntity
     *
     * @param weatherDTO
     * @return WeatherEntity
     */
    public WeatherEntity convertToEntity(CurrentWeatherDTO weatherDTO) {
        ModelMapper modelMapper = new ModelMapper();
        WeatherEntity result = modelMapper.map(weatherDTO, WeatherEntity.class);
        result.setIcon(weatherDTO.getWeather().get(0).getIcon());
        result.setNameCity(weatherDTO.getName());
        result.setIdCity(weatherDTO.getId());
        result.setDate(new Timestamp(System.currentTimeMillis()));
        result.setTemp(weatherDTO.getMain().getTemp());
        result.setDescription(weatherDTO.getWeather().get(0).getDescription());
        result.setWind(weatherDTO.getWind().getSpeed());
        result.setHumidity(weatherDTO.getMain().getHumidity());
        result.setPressure(weatherDTO.getMain().getPressure());

        return result;
    }
}
