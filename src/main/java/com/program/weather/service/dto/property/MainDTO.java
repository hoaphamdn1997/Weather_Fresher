package com.program.weather.service.dto.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MainDTO {
	
		private int temp;
		private String pressure;
		private String humidity;
		private String temp_min;
		private String temp_max;
		
		
}
