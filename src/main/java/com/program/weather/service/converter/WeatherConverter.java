package com.program.weather.service.converter;

import com.program.weather.dto.CurrentWeatherDTO;
import com.program.weather.entity.WeatherEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component //bien 1 cai class thanh 1op bean
public class WeatherConverter {
	
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
//	Tuy nhiên từ góc độ thiết kế thì chúng ta cần phân rõ 3 Annotation này cho các Class đảm nhiệm đúng nhiệm vụ của nó.
//
//@Service gắn cho các Bean đảm nhiệm xử lý logic
//@Repository gắn cho các Bean đảm nhiệm giao tiếp với DB
//@Component gắn cho các Bean khác.