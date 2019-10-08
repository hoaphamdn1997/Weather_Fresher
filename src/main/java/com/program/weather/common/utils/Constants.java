package com.program.weather.common.utils;

public class Constants {
	//API
	public static  final String API="http://api.openweathermap.org/data/2.5/";
	// APP ID
	public static final String 	APPID        = "&APPID=03d81f7b88e51dc1dfaf1b8b7b950ee8&units=imperial";
	//Weather Seach
	public static final String 	WEATHER_URL  = API+"/weather?q=";
	//Detail Weather
	public static final String 	FORECAST_URL = API+"/forecast?q=";
	//Link ICon
	public static final String	IMG_URL      ="http://openweathermap.org/img/wn/";
	//tail icon
	public static final String 	PNG          =".png";
	//Account Status
	public static final Boolean ACTIVE 		= true;
	public static final Boolean UN_ACTIVE	= false;
	//ROLE
	public static final String  ADMIN		 = "ROLE_ADMIN";
	public static final String  USER		 = "ROLE_USER";
}
