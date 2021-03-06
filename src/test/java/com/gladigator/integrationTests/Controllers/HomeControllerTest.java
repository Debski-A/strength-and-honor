package com.gladigator.integrationTests.Controllers;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.lang.reflect.Field;
import java.util.Locale;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.DispatcherServlet;

import com.gladigator.Controllers.HomeController;
import com.gladigator.Controllers.Advices.ApplicationExceptionsHandler;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/gladigator/Configs/sah-servlet.xml",
		"classpath:com/gladigator/Configs/test-dao-context.xml",
		"classpath:com/gladigator/Configs/security-context.xml",
		"classpath:com/gladigator/Configs/service-context.xml" })
public class HomeControllerTest {

	@Autowired
	private HomeController controller;

	@Autowired
	private ApplicationExceptionsHandler exceptionController;

	private MockMvc mockMvc;

	@Before
	public void before() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionController).build();
		throwExceptionIfNoHandlerFound(mockMvc);
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller, notNullValue());
	}

	@Test
	public void whenHomeIsCalled_ThenReturnHomepage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("homepage"));
	}

	@Test
	public void whenInvalidUrl_Then404() throws Exception {

		mockMvc.perform(get("/invalidURL")).andExpect(status().isOk()).andExpect(view().name("pagenotfound"));

	}

	@Test
	public void whenServiceExceptionIsThrown_ThenShowInernalErrorPage() throws Exception {

	}

	// Potrzebne aby podczas testow rzucany byl wyjatek NoHandlerFoundException
	private void throwExceptionIfNoHandlerFound(MockMvc mvc) throws NoSuchFieldException, IllegalAccessException {
		final Field field = MockMvc.class.getDeclaredField("servlet");
		field.setAccessible(true);
		final DispatcherServlet servlet = (DispatcherServlet) field.get(mvc);
		servlet.setThrowExceptionIfNoHandlerFound(true);
	}

	@Test
	public void showHomepageShouldReturnHomepageView() throws Exception {
		mockMvc.perform(get("/post")).andExpect(status().isOk()).andExpect(view().name("homepage"));
	}

	@Transactional
	@Test
	public void test() throws Exception {
		mockMvc.perform(post("/post").principal(() -> "Admin").content("{ \"content\": \"some content\", \"latestUpdate\": \"2019-03-04\" }").with(csrf()).contentType(MediaType.APPLICATION_JSON_UTF8).locale(Locale.UK))
				.andExpect(status().isOk());
	}

}
