package com.program.weather.common.utils;

public class Constants {
    //API
    public static final String API = "http://api.openweathermap.org";
    //VESION API
    public static final String VESION = "/data/2.5/";
    // APP ID
    public static final String APPID = "&APPID=03d81f7b88e51dc1dfaf1b8b7b950ee8&units=imperial";
    //WEATHE SEACH
    public static final String WEATHER_URL = API + VESION + "/weather?q=";
    //DETAIL WEATHER
    public static final String FORECAST_URL = API + VESION + "/forecast?q=";
    //HOST IMG
    public static final String HOSTIMG = "http://openweathermap.org";
    //ICON
    public static final String IMG_URL = HOSTIMG + "/img/wn/";
    //TAIL ICON
    public static final String PNG = ".png";
    //ACCONT STATUS
    public static final Boolean ACTIVE = true;
    public static final Boolean UN_ACTIVE = false;
    //ROLE
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String GUEST = "ROLE_GUEST";
    //SIZE FORECAST WEATHER
    public static final int SIZE_FORECAST_WEATHER = 40;
    public static final int SIZE_FORECAST_WEATHER_REPEAT = 8 ;



}
