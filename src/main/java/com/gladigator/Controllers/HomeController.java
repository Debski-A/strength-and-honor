package com.gladigator.Controllers;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage() {

		return "homepage";
	}
	
	@GetMapping("/weather")
	public String showWeatherPage() {

		return "weatherpage";
	}

	@GetMapping("/weatherConditions")
	public ResponseEntity<String> showWeatherConditions() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String uri = "https://query.yahooapis.com/v1/public/yql?q=select item.condition, wind.speed, atmosphere.pressure, astronomy.sunrise, astronomy.sunset, location.city, location.country, location.region from weather.forecast where woeid in (select woeid from geo.places(1) where text='Ozorków') AND u='c'";
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		return result;
	}

}
