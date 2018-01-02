package com.gladigator.unitTests.Controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Controllers.ProfileUtils;
import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.User;
import com.gladigator.Entities.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class ProfileUtilsTest {
	
	@InjectMocks
	private ProfileUtils profileUtils;
	
	@Mock
	private User user;
	@Mock
	private UserDetails userDetails;
	@Mock
	private BodyTypeDao bodyTypeDao;
	@Mock
	private SexDao sexDao;
	@Mock
	private FrequencyOfActivityDao foaDao;
	
	@Before
	public void before() {
		profileUtils = new ProfileUtils();
	}
	
	@Test
	public void whenObtainUserDetails_AndUserHasUserDetails_ThenGetUserDetailsFromUserAndReturnIt() throws Exception {
		when(user.getUserDetails()).thenReturn(userDetails);
		
		assertThat(profileUtils.obtainUserDetails(user), equalTo(userDetails));
		
		verify(user, times(2)).getUserDetails();
	}
	
	@Test
	public void whenObtainUserDetails_AndUserDoesNotHaveUserDetails_ThenReturnUserDetails() throws Exception {
		
		assertThat(profileUtils.obtainUserDetails(user), any(UserDetails.class));
		
		verify(user, times(1)).getUserDetails();
	}
	
	@Test
	public void whenAddListsOfAttributesToModel_ThenObtainListsFromDaos_AndAddToModel() throws Exception {
		
	}
	

}
