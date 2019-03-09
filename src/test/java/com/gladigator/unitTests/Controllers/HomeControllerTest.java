package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.gladigator.Controllers.HomeController;
import com.gladigator.Controllers.Utils.HomeUtils;
import com.gladigator.Services.PostService;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@Mock
	private PostService postService;

	@Mock
	private HomeUtils utils;
	
	@InjectMocks
	private HomeController controller;
	

	@Test
	public void whenShowHomePage_ThenReturnHomepage() throws Exception {
		//given
		ModelAndView mav = new ModelAndView("homepage");
		
		assertThat(controller.showHomePage(null, new Locale("pl", "PL")).getView(), equalTo(mav.getView()));
	}
	
	@Test
	public void whenShowWheatherPage_ThenReturnWeatherpage() throws Exception {
		assertThat(controller.showWeatherPage(), equalTo("weatherpage"));
	}
	
}
