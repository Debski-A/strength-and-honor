package com.gladigator.Controllers.RestUrls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ResponseEntityBuilder {
	
	public static final String NAME_OF_THE_CITY = "NameOfTheCity";
	
	@Autowired
	@Qualifier(value = "openWeatherApiImpl")
	private WeatherApi restUrl;
	
	private RestTemplate restTemplate;
	private HttpHeaders httpHeaders;
	
	public ResponseEntityBuilder() {
		this.restTemplate =  new RestTemplate();
		this.httpHeaders = new HttpHeaders();
	}

	public void setAcceptableMediaTypes(List<MediaType> mediaTypes) {
		this.httpHeaders.setAccept(mediaTypes);
	}
	
	public ResponseEntity<String> callWeatherApi(String param) {
		HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
		String url = restUrl.getUrl().replaceAll(NAME_OF_THE_CITY, param);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return responseEntity;
	}

	
}
