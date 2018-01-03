package com.gladigator.unitTests.Services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Exceptions.ServiceException;
import com.gladigator.Services.BodyTypeService;
import com.gladigator.Services.BodyTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BodyTypeServiceTest {
	
	@InjectMocks
	private BodyTypeService bodyTypeService = new BodyTypeServiceImpl();
	
	@Mock
	private BodyTypeDao bodyTypeDao;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private BodyType ectomorphic;
	
	@Before
	public void before() {
		ectomorphic = new BodyType();
	}
	
	@Test
	public void whenFindById_ThenInvokeDaoMethod_AndReturnBodyType() throws Exception {
		when(bodyTypeDao.findById(1)).thenReturn(ectomorphic);
		
		assertThat(bodyTypeService.findById(1), equalTo(ectomorphic));
		
		verify(bodyTypeDao).findById(1);
	}
	
	@Test
	public void whenFindById_AndAnyExceptionOccurrs_ThenThrowServiceException() throws Exception {
		exception.expect(ServiceException.class);
		exception.expectMessage("An Exception occurred");
		when(bodyTypeDao.findById(16)).thenThrow(Exception.class);
		
		bodyTypeService.findById(16);
	}
	
	@Test
	public void whenSaveOrUpdate_ThenInvokeDaoMethod() throws Exception {
		bodyTypeService.saveOrUpdate(ectomorphic);
		
		verify(bodyTypeDao).saveOrUpdate(ectomorphic);
	}
	
	@Test
	public void whenSaveOrUpdate_AndAnyExceptionOccurrs_ThenThrowServiceException() throws Exception {
		exception.expect(ServiceException.class);
		exception.expectMessage("An Exception occurred");
		doThrow(Exception.class).when(bodyTypeDao).saveOrUpdate(ectomorphic);
		
		bodyTypeService.saveOrUpdate(ectomorphic);
	}

}
