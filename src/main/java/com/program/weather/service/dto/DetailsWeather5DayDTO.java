package com.program.weather.service.dto;



import lombok.Data;

import java.time.Instant;
@Data
public class DetailsWeather5DayDTO {
    private Instant day;
    private String image;
    private String tempMin;
    private String tempMax;
    private String description;
    private String wind;
    private String humidity;
    private String pressure;
    private String clouds;
    private String city;

    public DetailsWeather5DayDTO(String image, String city, String tempMin, String tempMax, String wind, String humidity,
                                String pressure, String description, Instant day) {
        super();
        this.image = image;
        this.city = city;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.wind = wind;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.day = day;
    }
}
