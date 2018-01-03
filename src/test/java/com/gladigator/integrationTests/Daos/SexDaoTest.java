package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.Sex;

public class SexDaoTest extends GenericDaoTestUtils<Sex>{	
	
	@Autowired
	private SexDao sexDao;
	
	@Test
	public void whenGetAllFre_ThenReturnListOfSexes() throws Exception {
		Sex male = new Sex();
		male.setSexId(1);
		male.setSexType("male");
		Sex female = new Sex();
		female.setSexId(2);
		female.setSexType("female");
		
		assertThat(sexDao.getAll(), equalTo(Arrays.asList(male, female)));
	}
	
	@Test
	public void whenFindById2_ThenReturnFemale() throws Exception {
		Sex female = new Sex();
		female.setSexId(2);
		female.setSexType("female");
		
		assertThat(super.whenFindById_ThenReturnEntity(sexDao, 2), equalTo(female));
	}
	
//	Ponizsze trzy metody korzystaja z GenericDaoTestUtils, ktore jest zalezne od GenericDao i ich logika jest identyczna dla kazdej klasy testowej.
//	Wystarczy przetestowac jedna klase rozszerzajaca GenericDao
//	
//	@DirtiesContext
//	@Test
//	public void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
//		
//		
//		super.whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(foaDao);
//	}
//	
//	@Test
//	public void whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException() throws Exception{
//		
//		
//		super.whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException(sexDao, null);
//	}
//	
//	@Test
//	public void whenFindById_AndFrequencyOfActivityIsNull_ThenThrowRepositoryException() throws Exception {
//		
//		
//		super.whenFindById_AndBodyTypeIsNull_ThenThrowRepositoryException(sexDao, 8, Sex.class);
//	}
}
