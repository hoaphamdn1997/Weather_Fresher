package com.program.weather.service.dto.property;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Weather dto.
 */
@Data
@NoArgsConstructor
public class WeatherDTO {

    private String id;
    private String main;
    private String description;
    private String icon;


}
