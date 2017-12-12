package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gladigator.Controllers.CustomPasswordEncoder;
import com.gladigator.Controllers.PasswordValidator;
import com.gladigator.Controllers.RegisterController;
import com.gladigator.Controllers.RegisterUtils;
import com.gladigator.Entities.User;
import com.gladigator.Services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

	@Mock
	private UserService userService;
	@Mock
	private MessageSource messageSource;
	@Mock
	private RegisterUtils registerUtils;
	@Mock
	private PasswordValidator passwordValidator;
	@Mock
	private CustomPasswordEncoder encoder;
	
	@InjectMocks
	private RegisterController controller;
	
	private User testUser;
	private Model model;
	private Locale locale;
	private Map<String, String> params;
	
	@Before
	public void before() {
		testUser = new User("adam", "password1", "adam@gmail.com", null, false);
		model = mock(Model.class);
		locale = new Locale("pl");
	}
	
	@Test
	public void whenShowRegistrationPage_ThenReturnRegisterpage() throws Exception {
		
		
		assertThat(controller.showRegistrationPage(model, testUser), equalTo("registerpage"));
	}
	
	@Test
	public void whenShowRegistrationPage_ThenTestUserIsAddedToModel() throws Exception {
		
		controller.showRegistrationPage(model, testUser);
		
		verify(model).addAttribute(anyString(), any());
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenReturnRegisterPage() throws Exception {
		assertThat(controller.processRegistrationForm(model, testUser, mock(BindingResult.class), mock(HttpServletRequest.class), locale), equalTo("registerpage"));
	}
	
	@Test
	public void whenProcessRegistrationForm_AndUsernameOrEmailAreTaken_ThenAddErrorMessageToModel_AndRejectEmail() throws Exception {
		BindingResult bindingResult = mock(BindingResult.class);
		when(userService.checkIfUsernameOrEmailAreTaken(anyString(), anyString())).thenReturn(true);
		when(messageSource.getMessage("registerpage.emailOrUsernameAlreadyTaken", null, locale)).thenReturn("Username or Email already taken");
		
		controller.processRegistrationForm(model, testUser, bindingResult, mock(HttpServletRequest.class), locale);
		
		verify(model).addAttribute("errorMessage", "Username or Email already taken");
		verify(messageSource).getMessage("registerpage.emailOrUsernameAlreadyTaken", null, locale);
		verify(bindingResult).reject("email");
		
	}
	
	@Test
	public void whenProcessRegistrationForm_AndBindingResultHasErrors_ThenNoManipulationOnModel() throws Exception {
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		
		controller.processRegistrationForm(model, testUser, bindingResult, mock(HttpServletRequest.class), locale);
		
		verify(bindingResult).hasErrors();
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenUserIsDisableddAndTokenIsGenerated() throws Exception {
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);
		when(registerUtils.generateToken()).thenReturn("12345");
		
		controller.processRegistrationForm(model, testUser, bindingResult, mock(HttpServletRequest.class), locale);
		
		assertThat(testUser.getConfirmationToken(), equalTo("12345"));
		assertThat(testUser.getEnabled(), equalTo(false));
		
		verify(registerUtils).generateToken();
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenUserServiceSaveOrUpdateIsInvoked() throws Exception {
		
		controller.processRegistrationForm(model, testUser, mock(BindingResult.class), mock(HttpServletRequest.class), locale);
		
		verify(userService).saveOrUpdateUser(testUser);
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenLinkIsGenerated() throws Exception {
		when(registerUtils.generateToken()).thenReturn("12345");
		HttpServletRequest request = mock(HttpServletRequest.class);
		controller.processRegistrationForm(model, testUser, mock(BindingResult.class), request, locale);
		
		verify(registerUtils).createLink(request, "12345");
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenRegistrationEmailIsSent() throws Exception {
		when(registerUtils.generateToken()).thenReturn("12345");
		when(registerUtils.createLink(any(HttpServletRequest.class), anyString())).thenReturn("registrationLink");
		
		controller.processRegistrationForm(model, testUser, mock(BindingResult.class), mock(HttpServletRequest.class), locale);
		
		verify(registerUtils).sendRegistrationLink("registrationLink", testUser.getEmail(), locale);
	}
	
	@Test
	public void whenProcessRegistrationForm_ThenAddConfirmationMessageToModel() throws Exception {
		when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("User is saved");
		
		controller.processRegistrationForm(model, testUser, mock(BindingResult.class), mock(HttpServletRequest.class), locale);
		
		verify(model).addAttribute("confirmationMessage", "User is saved");
	}
	
	@Test
	public void whenShowConfirmationPage_ThenReturnConfirmpage() throws Exception {
		
		
		assertThat(controller.showConfirmationPage("xxx", model, "1234", locale), equalTo("confirmpage"));
	}
	
	@Test
	public void whenShowConfirmationPage_AndUserIsNull_ThenAddInvalidTokenToModel() throws Exception {
		when(userService.getUserByToken(anyString())).thenReturn(null);
		when(messageSource.getMessage("confirmpage.invalidToken", null, locale)).thenReturn("Token is invalid");
		
		controller.showConfirmationPage("xxx", model, "1234", locale);
		
		verify(model).addAttribute("invalidToken", "Token is invalid");
	}
	
	@Test
	public void whenShowConfirmationPage_ThenAddConfirmationTokenAndPasswordToModel() throws Exception {
		User user = mock(User.class);
		when(userService.getUserByToken("1234")).thenReturn(user);
		when(user.getConfirmationToken()).thenReturn("1234");
		
		controller.showConfirmationPage("xxx", model, "1234", locale);
		
		verify(model).addAttribute("confirmationToken", "1234");
		verify(model).addAttribute("password", "");
	}
	
	@Test
	public void whenProcessConfirmationPage_ThenRedirectLogin() throws Exception {
		prepareProcessConfirmationPageTests();
		when(userService.getUserByToken("1234")).thenReturn(mock(User.class));
		
		assertThat(controller.processConfirmationForm(params, mock(RedirectAttributes.class), locale), equalTo("redirect:login"));	
	}
	
	@Test
	public void whenProcessConfirmationPage_AndPasswordToWeak_ThenAddFlashAttributeErrorMessage_AndRedirect() throws Exception {
		prepareProcessConfirmationPageTests();
		RedirectAttributes redirAttributes = mock(RedirectAttributes.class);
		when(passwordValidator.isPasswordToWeak("xyz")).thenReturn(true);
		when(messageSource.getMessage("confirmpage.passwordToWeak", null, locale)).thenReturn("Password to weak");
		
		assertThat(controller.processConfirmationForm(params, redirAttributes, locale), equalTo("redirect:confirm?token=1234"));
		verify(passwordValidator).isPasswordToWeak("xyz");
		verify(messageSource).getMessage("confirmpage.passwordToWeak", null, locale);
		verify(redirAttributes).addFlashAttribute("errorMessage", "Password to weak");
	}
	

	/*@Test
	public void whenProcessConfirmationPage_ThenPasswordIsEncrypted() throws Exception {
		prepareProcessConfirmationPageTests();
		when(userService.getUserByToken("1234")).thenReturn(mock(User.class));
		
		controller.processConfirmationForm(params, mock(RedirectAttributes.class), locale);
		
		verify(encoder).encodePassword(anyString());
	}*/
	
	@Test
	public void whenProcessConfirmationPage_ThenUserIsEnabled_AndConfirmationTokenIsErased_AndUserIsSaved() throws Exception {
		prepareProcessConfirmationPageTests();
		User user = mock(User.class);
		when(userService.getUserByToken("1234")).thenReturn(user);
		
		controller.processConfirmationForm(params, mock(RedirectAttributes.class), locale);
		
		verify(user).setEnabled(true);
		verify(user).setConfirmationToken(null);
		verify(userService).saveOrUpdateUser(user);
	}
	
	@Test
	public void whenProcessConfirmationPage_ThenAddFlashAttributeSuccessMessageToModel() throws Exception {
		prepareProcessConfirmationPageTests();
		when(userService.getUserByToken("1234")).thenReturn(mock(User.class));
		RedirectAttributes redirAttributes = mock(RedirectAttributes.class);
		
		controller.processConfirmationForm(params, redirAttributes, locale);
		
		verify(redirAttributes).addFlashAttribute(anyString(), any());
	}
	
	private void prepareProcessConfirmationPageTests() {
		params = new HashMap<>();
		params.put("password", "xyz");
		params.put("token", "1234");
	}
}
