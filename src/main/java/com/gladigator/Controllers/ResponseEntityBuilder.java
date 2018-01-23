package com.gladigator.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gladigator.Controllers.RestUrls.RestUrl;

@Component
public class ResponseEntityBuilder {
	
	@Autowired
	private RestUrl restUrl;
	
	private RestTemplate restTemplate;
	private HttpHeaders httpHeaders;
	
	public ResponseEntityBuilder() {
		this.restTemplate =  new RestTemplate();
		this.httpHeaders = new HttpHeaders();
	}

	public void setAcceptableMediaTypes(List<MediaType> mediaTypes) {
		this.httpHeaders.setAccept(mediaTypes);
	}
	
	public ResponseEntity<String> createResponseEntity() {
		HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl.getUrl(), HttpMethod.GET, entity, String.class);
		return responseEntity;
	}

	
}
