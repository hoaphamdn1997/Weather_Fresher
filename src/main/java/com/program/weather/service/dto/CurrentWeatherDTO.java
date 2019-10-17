package com.program.weather.service.dto;

import com.program.weather.service.dto.property.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CurrentWeatherDTO {
	
	private CoordDTO    coord;
	private List<WeatherDTO> weather = new ArrayList<WeatherDTO>();
	private String   base;
	private MainDTO main;
	private String   visibility;
	private WindDTO wind;
	private CloudsDTO clouds;
	private String   dt;
	private SysDTO sys;
	private String   timezone;
	private String   id;
	private String   name;
	private String   cod;
	
	
	
	
}
