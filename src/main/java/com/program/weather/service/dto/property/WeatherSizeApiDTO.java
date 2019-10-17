package com.program.weather.service.dto.property;

public class WeatherSizeApiDTO {
    private String city;
    private Integer size;

    public void setCity(String city) {
        this.city = city;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public String getCity() {
        return city;
    }
}
