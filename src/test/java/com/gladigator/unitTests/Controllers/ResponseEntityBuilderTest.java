package com.gladigator.unitTests.Controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.gladigator.Controllers.RestUrls.ResponseEntityBuilder;
import com.gladigator.Controllers.RestUrls.WeatherApi;

import static com.gladigator.Controllers.RestUrls.ResponseEntityBuilder.NAME_OF_THE_CITY;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

import java.util.Locale;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ResponseEntityBuilderTest {

	@Mock
	private WeatherApi restUrl;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private HttpHeaders httpHeaders;
	
	@InjectMocks
	private ResponseEntityBuilder responseEntityBuilder;
	
	private static final String FAKE_URL = "http://fake-url.com/city=" + NAME_OF_THE_CITY;
	private static final String FAKE_URL_RESULT = "http://fake-url.com/city=Warszawa&lang=pl";
	
	@Test
	public void whenCallWeatherApi_ThenReturnResponseEntity() throws Exception {
		//given
		when(restUrl.getUrl()).thenReturn(FAKE_URL);
		//when
		responseEntityBuilder.callWeatherApi("Warszawa", new Locale("pl", "PL"));
		//then
		verify(restTemplate).exchange(eq(FAKE_URL_RESULT), eq(HttpMethod.GET), Mockito.any(), eq(String.class));
	}
	
}

