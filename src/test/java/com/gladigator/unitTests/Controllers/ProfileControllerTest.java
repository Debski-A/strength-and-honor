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
import com.gladigator.Entities.CalculateBMIResponse;
import com.gladigator.Entities.CalculateBMRResponse;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserService;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.ws.client.WebServiceIOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.InvalidParameterException;
import java.security.Principal;
import java.util.Locale;


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
		userDetails = new UserDetails();
		when(profileUtils.obtainUserDetails(principal)).thenReturn(userDetails);
		locale = new Locale("pl-PL");
	}

	@Test
	public void whenShowProfilePage_ThenAddUserDetailsToModel_AndReturnProfilepage() throws Exception {
		// Given
		// When Then
		assertThat(controller.showProfile(model, principal, locale), equalTo("profilepage"));
		verify(model).addAttribute("userDetails", userDetails);
	}

	@Test
	public void whenShowProfilePage_ThenAddUserDetailsToModel() throws Exception {
		controller.showProfile(model, principal, locale);

		verify(model).addAttribute("userDetails", userDetails);
	}

	@Test
	public void whenProcessProfilePage_ThenReturnRedirectProfile() throws Exception {

		assertThat(controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult,
				mock(SessionStatus.class), locale), equalTo("redirect:profile"));
	}

	@Test
	public void whenProcessProfilePage_ThenSaveOrUpdateUser() throws Exception {
		controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult,
				mock(SessionStatus.class), locale);

		verify(userService).saveOrUpdateUserDetails(userDetails);
	}

	@Test
	public void whenProcessProfilePage_AndBindingResultHasErrors_ThenReturnProfilepageWithoutSavingUser()
			throws Exception {
		when(bindingResult.hasErrors()).thenReturn(true);

		controller.processProfilePage(mock(RedirectAttributes.class), userDetails, bindingResult,
				mock(SessionStatus.class), locale);

		verify(userService, never()).saveOrUpdateUserDetails(userDetails);
	}

	@Test
	public void whenCalculateBmr_ThenRedirectToProfile() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMRResponse(userDetails)).thenReturn(new CalculateBMRResponse() {
			{
				setCalculatedBMRResponse("666");
			}
		});
		// When
		String result = controller.calculateBmr(userDetails, bindingResult, redirAttr, locale);
		// Then
		assertEquals("redirect:profile", result);
		assertThat(userDetails.getBmr(), is(666D));
	}

	@Test
	public void whenCalculateBmr_AndModelIsNotValid_ThenAddMissingParamsError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(bindingResult.hasErrors()).thenReturn(true);
		// When
		controller.calculateBmr(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmr.missingParams", null, locale);

	}

	@Test
	public void whenCalculateBmr_AndWebServiceIOExceptionOccurs_ThenAddConnectionProblemError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMRResponse(userDetails)).thenThrow(WebServiceIOException.class);
		// When
		controller.calculateBmr(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmr.connectionProblem", null, locale);

	}

	@Test
	public void whenCalculateBmr_AndInvalidParameterExceptionOccurs_ThenAddMissingParamsError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMRResponse(userDetails)).thenThrow(InvalidParameterException.class);
		// When
		controller.calculateBmr(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmr.missingParams", null, locale);

	}

	@Test
	public void whenCalculateBmi_ThenRedirectProfile() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMIResponse(userDetails)).thenReturn(new CalculateBMIResponse() {
			{
				setCalculatedBMI("666");
			}
		});
		// When
		String result = controller.calculateBmi(userDetails, bindingResult, redirAttr, locale);
		// Then
		assertEquals("redirect:profile", result);
		assertThat(userDetails.getBmi(), is(666D));
	}
	
	@Test
	public void whenCalculateBmI_AndModelIsNotValid_ThenAddMissingParamsError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(bindingResult.hasErrors()).thenReturn(true);
		// When
		controller.calculateBmi(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmi.missingParams", null, locale);

	}
	
	@Test
	public void whenCalculateBmi_AndWebServiceIOExceptionOccurs_ThenAddConnectionProblemError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMIResponse(userDetails)).thenThrow(WebServiceIOException.class);
		// When
		controller.calculateBmi(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmi.connectionProblem", null, locale);

	}
	
	@Test
	public void whenCalculateBmi_AndInvalidParameterExceptionOccurs_ThenAddMissingParamsError() throws Exception {
		// Given
		RedirectAttributes redirAttr = new RedirectAttributesModelMap();
		when(profileUtils.prepareBMIResponse(userDetails)).thenThrow(InvalidParameterException.class);
		// When
		controller.calculateBmi(userDetails, bindingResult, redirAttr, locale);
		// Then
		verify(messageSource).getMessage("userDetails.bmi.missingParams", null, locale);

	}

}
