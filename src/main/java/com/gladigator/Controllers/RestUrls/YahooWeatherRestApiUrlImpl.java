package com.gladigator.Controllers.RestUrls;

import org.springframework.stereotype.Component;

@Component
public class YahooWeatherRestApiUrlImpl implements RestUrl{
	
	private final String URL = "https://query.yahooapis.com/v1/public/yql?q=select item.condition, wind.speed, atmosphere.pressure, astronomy.sunrise, astronomy.sunset, location.city, location.country, location.region from weather.forecast where woeid in (select woeid from geo.places(1) where text='Ozorków') AND u='c'";

	@Override
	public String getUrl() {
		return this.URL;
	}


}
