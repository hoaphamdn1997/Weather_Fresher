package com.program.weather.service.dto.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysDTO {
	private String type;
	private String id;
	private String message;
	private String country;
	private String sunrise;
	private String sunset;
	
}
