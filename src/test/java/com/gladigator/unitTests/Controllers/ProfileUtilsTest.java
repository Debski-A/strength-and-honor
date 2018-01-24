package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import com.gladigator.Controllers.Utils.ProfileUtils;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;
import com.gladigator.Services.UserDetailsService;
import com.gladigator.Services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class ProfileUtilsTest {
	
	@InjectMocks
	private ProfileUtils profileUtils = new ProfileUtils();
	@Mock
	private UserDetailsService userDetailsService;
	@Mock
	private UserService userService;
	@Mock
	private User user;
	@Mock
	private UserDetails userDetails;
	@Mock
	private Principal principal;
	
	@Before
	public void before() {
		when(principal.getName()).thenReturn("some username");
		when(userService.getUserByUsername("some username")).thenReturn(user);
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
		when(userDetailsService.getSelectiveDetailsAsMap()).thenReturn(listOfSelectives);
		
		profileUtils.addListsOfAttributesToModel(model);
		
		verify(userDetailsService).getSelectiveDetailsAsMap();
		verify(model).addAllAttributes(listOfSelectives);
	}
	
	

}
