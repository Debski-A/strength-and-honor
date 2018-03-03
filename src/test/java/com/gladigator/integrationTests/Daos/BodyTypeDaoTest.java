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
		BodyType none = new BodyType();
		none.setBodyTypeId(1);
		none.setBodyTypeType("none");
		
		BodyType ectomorphic = new BodyType();
		ectomorphic.setBodyTypeId(2);
		ectomorphic.setBodyTypeType("ectomorphic");
		
		BodyType mesomorphic = new BodyType();
		mesomorphic.setBodyTypeId(3);
		mesomorphic.setBodyTypeType("mesomorphic");
		
		BodyType endomorphic = new BodyType();
		endomorphic.setBodyTypeId(4);
		endomorphic.setBodyTypeType("endomorphic");
		
		assertThat(bodyTypeDao.getAll(), equalTo(Arrays.asList(none, ectomorphic, mesomorphic, endomorphic)));
	}
	
	@DirtiesContext
	@Test
	public void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
		
		
		super.whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(bodyTypeDao);
	}
	
	@Test
	public void whenFindById2_ThenReturnBodyTypeEctomorphic() throws Exception {
		BodyType ectomorphic = new BodyType();
		ectomorphic.setBodyTypeId(2);
		ectomorphic.setBodyTypeType("ectomorphic");
		
		assertThat(super.whenFindById_ThenReturnEntity(bodyTypeDao, 2), equalTo(ectomorphic));
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
