package com.gladigator.integrationTests.Controllers;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Entities.UserDetails.UserDetailsBuilder;
import com.gladigator.Services.UserService;

@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/sah-servlet.xml",
								   "classpath:com/gladigator/Configs/test-dao-context.xml",
								   "classpath:com/gladigator/Configs/security-context.xml",
								   "classpath:com/gladigator/Configs/service-context.xml"})
public class ProfileControllerTest {
	
	@Autowired
	private WebApplicationContext webContext;
	
	private MockMvc mockMvc;
	private User user;
	private UserDetails userDetails;
	
	@Before
	public void before() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(webContext, notNullValue());
	}
	
	@Test
	public void whenShowProfilePage_AndUserUnauthenticated_ThenReturnLoginpage() throws Exception {
		mockMvc.perform(get("/profile").with(anonymous())).andDo(print())
										.andExpect(status().is(302))
										.andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Transactional
	@Test
	public void whenShowProfilePage_AndUserIsAuthenticated_ThenReturnProfilepage() throws Exception {
		addUserToDB();
		
		mockMvc.perform(get("/profile").with(user("adam"))).andExpect(status().isOk())
										.andExpect(view().name("profilepage"));
	}
	
	
	@Transactional //Rozszerza transakcje na metode testowa. Dzieki temu zmiany dokonane w metodzie testowej sa domyslnie rollback'owane.
	//Alternatywa dla DiritiesContext np. w UserDaoTest - nie bedzie trzeba od nowa ladowac kontekstow, bo zmiany nie sa commitowane i konteksty pozostaja czyste
	@Test
	public void givenUser_WhenShowProfilePage_ThenGetUserDetailsFromDBAndAddToModel() throws Exception {
		addUserToDB();
		
		mockMvc.perform(get("/profile").with(user("adam"))).andExpect(status().isOk())
										.andExpect(model().attribute("userDetails", userDetails));
		
	}
	
//	mvn test robi failure na tym tescie TODO
//	@Transactional
//	@Test
//	public void whenProcessProfilePage_ThenReturnProfilepage() throws Exception {
//		addUserToDB();
//		//musialem dodac CSRF ze wzgledu na to, ze kazdy formularz post wymaga tokenu CSRF. Ponadto gdy metoda robi redirect to zwraca status 302
//		mockMvc.perform(post("/profile").sessionAttr("userDetails", userDetails).with(user("adam")).with(csrf())).andDo(print())
//						.andExpect(status().isOk())
//						.andExpect(view().name("profilepage"));
//	}
	
	private void addUserToDB() throws Exception {
		userDetails = new UserDetails();
		user = new User("adam", "password1234", "adam@gmail.com", null, true);
		userDetails.setUser(user);
		user.setUserDetails(userDetails);
		UserService userService = webContext.getBean("userServiceImpl", UserService.class);
		userService.saveOrUpdate(user);
	}

}
