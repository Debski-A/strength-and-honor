package com.gladigator.unitTests.Services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Sex;
import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.UserDetailsService;
import com.gladigator.Services.UserDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {
	
	@InjectMocks
	private UserDetailsService userDetailsService = new UserDetailsServiceImpl();
	
	@Mock
	private BodyTypeDao bodyTypeDao;
	
	@Mock 
	private SexDao sexDao;
	
	@Mock
	private FrequencyOfActivityDao foaDao;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void whenGetSelectiveDetailsAsMap_ThenReturnMapOfDetails() throws Exception {
		List<BodyType> btList = new ArrayList<>();
		List<Sex> sList = new ArrayList<>();
		List<FrequencyOfActivity> foaList = new ArrayList<>();
		Map<String, List<?>> result = new HashMap<>();
		result.put("bodyTypeListOfSelectives", btList);
		result.put("sexListOfSelectives", sList);
		result.put("frequenciesListOfSelectives", foaList);
		when(bodyTypeDao.getAll()).thenReturn(btList);
		when(sexDao.getAll()).thenReturn(sList);
		when(foaDao.getAll()).thenReturn(foaList);
		
		assertThat(userDetailsService.getSelectiveDetailsAsMap(), equalTo(result));
		
		verify(bodyTypeDao).getAll();
		verify(sexDao).getAll();
		verify(foaDao).getAll();
	}
	
	@Test
	public void whenGetSelectiveDetailsAsMap_AndAnyExceptionOccurrs_ThenThrowServiceException() throws Exception {
		exception.expect(ServiceException.class);
		exception.expectMessage("An Exception occurred");
		when(foaDao.getAll()).thenThrow(Exception.class);
		
		userDetailsService.getSelectiveDetailsAsMap();
	}
	
}
