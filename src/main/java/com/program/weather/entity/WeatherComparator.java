package com.program.weather.entity;

import java.util.Comparator;
//This class was created to compare two dates with each other to see if they match
public class WeatherComparator implements Comparator<WeatherEntity>{

	@Override
	public int compare(WeatherEntity o1, WeatherEntity o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
