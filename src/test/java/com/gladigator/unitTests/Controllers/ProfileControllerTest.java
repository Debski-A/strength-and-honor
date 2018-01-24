package com.gladigator.unitTests.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.gladigator.Controllers.ProfileController;
import com.gladigator.Controllers.Utils.ProfileUtils;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserService;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private ProfileController controller;
	
	@Mock
	private Model model;
	@Mock
	private Principal principal;
	@Mock
	private BindingResult bindingResult;
	@Mock
	private UserDetails userDetails;
	@Mock
	private MessageSource messageSource;
	@Mock
	private User user;
	@Mock
	private ProfileUtils profileUtils;
	
	private Locale locale;
	
	@Before
	public void before() {
		when(profileUtils.obtainUserDetails(principal)).thenReturn(userDetails);
		locale = new Locale("pl");
	}
	
	@Test
	public void whenShowProfilePage_ThenReturnProfilepage() throws Exception {
		
		assertThat(controller.showProfilePage(model, principal), equalTo("profilepage"));
	}
	
	@Test
	public void whenShowProfilePage_ThenAddUserDetailsToModel() throws Exception {
		controller.showProfilePage(model, principal);
		
		verify(model).addAttribute(anyString(), any(UserDetails.class));
	}
	
	@Test
	public void whenProcessProfilePage_ThenReturnRedirectProfile() throws Exception {
		
		assertThat(controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult, mock(SessionStatus.class), locale), equalTo("redirect:profile"));
	}
	
	@Test
	public void whenProcessProfilePage_ThenSaveOrUpdateUser() throws Exception {
		controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult, mock(SessionStatus.class), locale);
		
		verify(userService).saveOrUpdateUserDetails(userDetails);
	}
	
	@Test
	public void whenProcessProfilePage_AndBindingResultHasErrors_ThenReturnProfilepageWithoutSavingUser() throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);
		
		controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult, mock(SessionStatus.class), locale);
		
		verify(userService, never()).saveOrUpdateUserDetails(userDetails);
	}
	
	

}
