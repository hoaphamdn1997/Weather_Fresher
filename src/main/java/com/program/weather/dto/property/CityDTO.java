package com.program.weather.dto.property;

import java.sql.Timestamp;
import java.util.List;

public class CityDTO {
    private Double id;
    private String name;
    private CoordDTO coord;
    private String country;
    private Integer sunrise;
    private Integer sunset;
    private String population;
    private Timestamp timezone;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordDTO getCoord() {
        return coord;
    }

    public void setCoord(CoordDTO coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Timestamp getTimezone() {
        return timezone;
    }

    public void setTimezone(Timestamp timezone) {
        this.timezone = timezone;
    }
}
