package com.gladigator.unitTests.Services;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Services.BodyTypeService;
import com.gladigator.Services.BodyTypeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BodyTypeServiceTest {
	
	@InjectMocks
	private BodyTypeService bodyTypeService = new BodyTypeServiceImpl();
	
	@Mock
	private BodyTypeDao bodyTypeDao;
	
	@Test
	public void whenFindById_ThenInvokeDaoMethod_AndReturnBodyType() throws Exception {
		//when(bodyTypeDao.))  TESTY DLA GENERICDAOIMPL.findById i .saveOrUpdate
	}

}
