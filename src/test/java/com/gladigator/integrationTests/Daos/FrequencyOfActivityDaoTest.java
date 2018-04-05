package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.BodyTypeTranslation;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.FrequencyOfActivityTranslation;

public class FrequencyOfActivityDaoTest extends GenericDaoTestUtils<FrequencyOfActivity> {
	
	@Autowired
	private FrequencyOfActivityDao foaDao;
	
	@Test
	public void whenFindById5_ThenReturnHighFrequency() throws Exception {
		FrequencyOfActivity high = new FrequencyOfActivity();
		FrequencyOfActivityTranslation highEngTranslation = new FrequencyOfActivityTranslation(5, "en-UK", true, "high");
		FrequencyOfActivityTranslation highPlTranslation = new FrequencyOfActivityTranslation(11, "pl-PL", false, "duża");
		high.setFrequencyOfActivityId(5);
		high.setFoaTranslations(Arrays.asList(highEngTranslation, highPlTranslation));;
		
		assertThat(high, is(equalTo(super.whenFindById_ThenReturnEntity(foaDao, 5))));
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
