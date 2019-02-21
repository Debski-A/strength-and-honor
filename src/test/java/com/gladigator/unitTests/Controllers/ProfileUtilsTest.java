package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.InvalidParameterException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import com.gladigator.Controllers.Utils.ProfileUtils;
import com.gladigator.Entities.CalculateBMIRequest;
import com.gladigator.Entities.CalculateBMIResponse;
import com.gladigator.Entities.CalculateBMRRequest;
import com.gladigator.Entities.CalculateBMRResponse;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Sex;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.BmiBmrServiceClient;
import com.gladigator.Services.UserDetailsService;
import com.gladigator.Services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ProfileUtilsTest {
	
	@InjectMocks
	private ProfileUtils profileUtils;
	@Mock
	private UserDetailsService userDetailsService;
	@Mock
	private UserService userService;
	@Mock
	private User user;
	@Mock
	private Principal principal;
	@Mock
	private BmiBmrServiceClient bmiBmrService;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private UserDetails userDetails;
	
	@Before
	public void before() {
		when(principal.getName()).thenReturn("some username");
		when(userService.getUserByUsername("some username")).thenReturn(user);
		userDetails = new UserDetails();
		userDetails.setFrequencyOfActivity(new FrequencyOfActivity());
		userDetails.setSex(new Sex());
	}
	
	@Test
	public void givenUserDetails_WhenPrepareBMIResponse_ThenReturnFilledBMIResponseObject() throws Exception {
	    // Given:
		CalculateBMIResponse preparedResponse = new CalculateBMIResponse();
		preparedResponse.setCalculatedBMI("666");
		when(bmiBmrService.callBmiService(Mockito.any(CalculateBMIRequest.class))).thenReturn(preparedResponse);
		userDetails.setHeight(666);
		userDetails.setWeight(666);
	    // When:
	    CalculateBMIResponse bmiResponse = profileUtils.prepareBMIResponse(userDetails);
	    // Then:
	    assertEquals("666", bmiResponse.getCalculatedBMI());
	}
	
	@Test
	public void givenInvalidUserDetails_WhenPrepareBMIResponse_ThenThrowException() throws Exception {
		// Given:
		userDetails.setHeight(null);
		// Then:
		expectedException.expect(InvalidParameterException.class);
		expectedException.expectMessage("Some user details wasn't filled");
		// When:
		profileUtils.prepareBMIResponse(userDetails);
	}
	
	
	@Test
	public void givenUserDetails_WhenPrepareBMRResponse_ThenReturnFilledBMRResponseObject() throws Exception {
	    // Given:
	    userDetails.setAge(28);
	    userDetails.getFrequencyOfActivity().setFrequencyOfActivityId(1);
	    userDetails.getSex().setSexId(2);
	    userDetails.setHeight(193);
	    userDetails.setWeight(105);
	    
	    CalculateBMRResponse preparedResponse = new CalculateBMRResponse();
	    preparedResponse.setCalculatedBMRResponse("666");
	    Mockito.when(bmiBmrService.callBmrService(Mockito.any(CalculateBMRRequest.class))).thenReturn(preparedResponse);
	    // When:
	    CalculateBMRResponse bmrResponse = profileUtils.prepareBMRResponse(userDetails);
	    // Then:
	    assertEquals("666", bmrResponse.getCalculatedBMRResponse());
	    Mockito.verify(bmiBmrService).callBmrService(Mockito.any(CalculateBMRRequest.class));
	}
	
	@Test
	public void givenInvalidUserDetails_WhenPrepareBMRResponse_ThenThrowException() throws Exception {
		// Given:
		userDetails.getFrequencyOfActivity().setFrequencyOfActivityId(3);
		userDetails.getSex().setSexId(3);
		userDetails.setHeight(null);
		// Then:
		expectedException.expect(InvalidParameterException.class);
		expectedException.expectMessage("Some user details wasn't filled");
		// When:
		profileUtils.prepareBMRResponse(userDetails);
	}
	
	@Test
	public void whenObtainUserDetails_AndUserHasUserDetails_ThenGetUserDetailsFromUserAndReturnIt() throws Exception {
		when(user.getUserDetails()).thenReturn(userDetails);
		
		assertThat(profileUtils.obtainUserDetails(principal), equalTo(userDetails));
		
		verify(user, times(3)).getUserDetails();
	}
	
	@Test
	public void whenObtainUserDetails_AndUserDoesNotHaveUserDetails_ThenReturnUserDetails() throws Exception {
		
		assertThat(profileUtils.obtainUserDetails(principal), any(UserDetails.class));
		
		verify(user, times(1)).getUserDetails();
	}
	
	@Test
	public void whenObtainUserDetails_ThenInvokdeGetUserByUsername() throws Exception {
		
		profileUtils.obtainUserDetails(principal);
		
		verify(userService).getUserByUsername("some username");
	}
	
	@Test
	public void whenAddListsOfAttributesToModel_ThenObtainListsFromServices_AndAddToModel() throws Exception {
		Model model = mock(Model.class);
		Map<String, List<?>> listOfSelectives = new HashMap<>();
		Locale locale = new Locale("pl-PL");
		when(userDetailsService.getSelectiveDetailsAsMap(locale)).thenReturn(listOfSelectives);
		
		profileUtils.addListsOfAttributesToModel(model, locale);
		
		verify(userDetailsService).getSelectiveDetailsAsMap(locale);
		verify(model).addAllAttributes(listOfSelectives);
	}
	
	

}
