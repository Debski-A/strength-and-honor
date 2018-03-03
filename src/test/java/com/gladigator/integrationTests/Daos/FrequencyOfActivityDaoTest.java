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
		FrequencyOfActivity none = new FrequencyOfActivity();
		none.setFrequencyOfActivityId(1);
		none.setFrequencyOfActivityFrequencyName("none");
		FrequencyOfActivity veryLow = new FrequencyOfActivity();
		veryLow.setFrequencyOfActivityId(2);
		veryLow.setFrequencyOfActivityFrequencyName("very low");
		FrequencyOfActivity low = new FrequencyOfActivity();
		low.setFrequencyOfActivityId(3);
		low.setFrequencyOfActivityFrequencyName("low");
		FrequencyOfActivity medium = new FrequencyOfActivity();
		medium.setFrequencyOfActivityId(4);
		medium.setFrequencyOfActivityFrequencyName("medium");
		FrequencyOfActivity high = new FrequencyOfActivity();
		high.setFrequencyOfActivityId(5);
		high.setFrequencyOfActivityFrequencyName("high");
		FrequencyOfActivity veryHigh = new FrequencyOfActivity();
		veryHigh.setFrequencyOfActivityId(6);
		veryHigh.setFrequencyOfActivityFrequencyName("very high");
		
		assertThat(foaDao.getAll(), equalTo(Arrays.asList(none,veryLow, low, medium, high, veryHigh)));
	}
	
	@Test
	public void whenFindById5_ThenReturnHighFrequency() throws Exception {
		FrequencyOfActivity high = new FrequencyOfActivity();
		high.setFrequencyOfActivityId(5);
		high.setFrequencyOfActivityFrequencyName("high");
		
		assertThat(super.whenFindById_ThenReturnEntity(foaDao, 5), equalTo(high));
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
