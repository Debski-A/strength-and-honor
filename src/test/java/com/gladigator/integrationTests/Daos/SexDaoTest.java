package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.Sex;
import com.gladigator.Entities.SexTranslation;

public class SexDaoTest extends GenericDaoTestUtils<Sex>{	
	
	@Autowired
	private SexDao sexDao;
	
	@Test
	public void whenGetAllFre_ThenReturnListOfSexes() throws Exception {
		Sex male = new Sex();
		SexTranslation maleEngTranslation = new SexTranslation(1, "en-UK", true, "male");
		SexTranslation malePlTranslation = new SexTranslation(3, "pl-PL", false, "mężczyzna");
		male.setSexId(1);
		male.setSexTranslations(Arrays.asList(maleEngTranslation, malePlTranslation));
		Sex female = new Sex();
		SexTranslation femaleEngTranslation = new SexTranslation(2, "en-UK", true, "female");
		SexTranslation femalePlTranslation = new SexTranslation(4, "pl-PL", false, "kobieta");
		female.setSexId(2);
		female.setSexTranslations(Arrays.asList(femaleEngTranslation, femalePlTranslation));
		
		assertThat(Arrays.asList(male, female), equalTo(sexDao.getAll()));
	}
	
	@Test
	public void whenFindById2_ThenReturnFemale() throws Exception {
		Sex female = new Sex();
		SexTranslation femaleEngTranslation = new SexTranslation(2, "en-UK", true, "female");
		SexTranslation femalePlTranslation = new SexTranslation(4, "pl-PL", false, "kobieta");
		female.setSexId(2);
		female.setSexTranslations(Arrays.asList(femaleEngTranslation, femalePlTranslation));
		Sex female2 = super.whenFindById_ThenReturnEntity(sexDao, 2);
		
		assertThat(female, equalTo(female2));
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
