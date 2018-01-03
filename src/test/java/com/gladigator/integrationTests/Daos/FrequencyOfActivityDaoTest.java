package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Entities.FrequencyOfActivity;

public class FrequencyOfActivityDaoTest extends GenericDaoTestUtils<FrequencyOfActivity> {
	
	@Autowired
	private FrequencyOfActivityDao foaDao;
	
	
	@Test
	public void whenGetAllFre_ThenReturnListOfFrequencies() throws Exception {
		FrequencyOfActivity veryLow = new FrequencyOfActivity();
		veryLow.setFrequencyOfActivityId(1);
		veryLow.setFrequencyOfActivityFrequencyName("very low");
		FrequencyOfActivity low = new FrequencyOfActivity();
		low.setFrequencyOfActivityId(2);
		low.setFrequencyOfActivityFrequencyName("low");
		FrequencyOfActivity medium = new FrequencyOfActivity();
		medium.setFrequencyOfActivityId(3);
		medium.setFrequencyOfActivityFrequencyName("medium");
		FrequencyOfActivity high = new FrequencyOfActivity();
		high.setFrequencyOfActivityId(4);
		high.setFrequencyOfActivityFrequencyName("high");
		FrequencyOfActivity veryHigh = new FrequencyOfActivity();
		veryHigh.setFrequencyOfActivityId(5);
		veryHigh.setFrequencyOfActivityFrequencyName("very high");
		
		assertThat(foaDao.getAll(), equalTo(Arrays.asList(veryLow, low, medium, high, veryHigh)));
	}
	
	@Test
	public void whenFindById4_ThenReturnHighFrequency() throws Exception {
		FrequencyOfActivity high = new FrequencyOfActivity();
		high.setFrequencyOfActivityId(4);
		high.setFrequencyOfActivityFrequencyName("high");
		
		assertThat(super.whenFindById_ThenReturnEntity(foaDao, 4), equalTo(high));
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
//		super.whenFindById_AndExceptionOccurrs_ThenThrowRepositoryException(foaDao, null);
//	}
//	
//	@Test
//	public void whenFindById_AndFrequencyOfActivityIsNull_ThenThrowRepositoryException() throws Exception {
//		
//		
//		super.whenFindById_AndBodyTypeIsNull_ThenThrowRepositoryException(foaDao, 8, FrequencyOfActivity.class);
//	}

}
