package com.gladigator.Controllers.RestUrls;

import static com.gladigator.Controllers.RestUrls.ResponseEntityBuilder.NAME_OF_THE_CITY;

import org.springframework.stereotype.Component;

@Component
public class OpenWeatherApiImpl implements WeatherApi {

	private final static String API_KEY = "6d069e7d36949c4f71209ee276d58e73";
	private final static String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=" + NAME_OF_THE_CITY + "&APPID=" + API_KEY +"&units=metric";

	@Override
	public String getUrl() {
		return API_URL;
	}

}
