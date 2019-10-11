package com.program.weather.entity;

import java.sql.Timestamp;
import java.time.Instant;

public class DetailsWeatherEntity {

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

    public DetailsWeatherEntity( String image,String city ,String tempMin,String tempMax,String wind,String humidity,
                                 String pressure,String description,Instant day) {
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

    public void setDay(Instant day) {
        this.day = day;
    }

    public Instant getDay() {
        return day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }



}
