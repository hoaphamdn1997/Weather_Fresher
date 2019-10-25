package com.program.weather.service.dto.property;

import lombok.Data;

import java.sql.Timestamp;

/**
 * The type City dto.
 */
@Data
public class CityDTO {
    private Double id;

    private String name;

    private CoordDTO coord;

    private String country;

    private Integer sunrise;

    private Integer sunset;

    private String population;

    private Timestamp timezone;

}
