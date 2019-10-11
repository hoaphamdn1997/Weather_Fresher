package com.program.weather.dto.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDTO {
	
	private String id;
	private String main;
	private String description;
	private String icon;
	
	
}
