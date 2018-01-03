package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Entities.BodyType;

public class BodyTypeDaoTest extends GenericDaoTestUtils<BodyType> {
	
	@Autowired
	private BodyTypeDao bodyTypeDao;
	
	@Test
	public void whenGetAll_ThenReturnListOfBodyTypes() throws Exception {
		BodyType ectomorphic = new BodyType();
		ectomorphic.setBodyTypeId(1);
		ectomorphic.setBodyTypeType("ectomorphic");
		
		BodyType mesomorphic = new BodyType();
		mesomorphic.setBodyTypeId(2);
		mesomorphic.setBodyTypeType("mesomorphic");
		
		BodyType endomorphic = new BodyType();
		endomorphic.setBodyTypeId(3);
		endomorphic.setBodyTypeType("endomorphic");
		
		assertThat(bodyTypeDao.getAll(), equalTo(Arrays.asList(ectomorphic, mesomorphic, endomorphic)));
	}
	
	@DirtiesContext
	@Test
	public void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
		
		
		super.whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(bodyTypeDao);
	}
	
	@Test
	public void whenFindById1_ThenReturnBodyTypeEctomorphic() throws Exception {
		BodyType ectomorphic = new BodyType();
		ectomorphic.setBodyTypeId(1);
		ectomorphic.setBodyTypeType("ectomorphic");
		
		assertThat(super.whenFindById_ThenReturnEntity(bodyTypeDao, 1), equalTo(ectomorphic));
	}
	
	@Test
	public void whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException() throws Exception{
		
		
		super.whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException(bodyTypeDao, null);
	}
	
	@Test
	public void whenFindById_AndBodyTypeIsNull_ThenThrowRepositoryException() throws Exception {
		
		
		super.whenFindById_AndBodyTypeIsNull_ThenThrowRepositoryException(bodyTypeDao, 8, BodyType.class);
	}
	
}
