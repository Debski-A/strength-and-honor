package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.BodyTypeTranslation;

public class BodyTypeDaoTest extends GenericDaoTestUtils<BodyType> {
	
	@Autowired
	private BodyTypeDao bodyTypeDao;
	
	@Test
	public void whenGetAll_ThenReturnListOfBodyTypes() throws Exception {
		BodyType none = new BodyType();
		BodyTypeTranslation noneEngTranslation = new BodyTypeTranslation(1, "en-GB", "none");
		BodyTypeTranslation nonePlTranslation = new BodyTypeTranslation(5, "pl-PL", "nieokreslono");
		none.setBodyTypeId(1);
		none.setBodyTypeTranslations(Arrays.asList(noneEngTranslation, nonePlTranslation));
		
		BodyType meso = new BodyType();
		BodyTypeTranslation mesoEngTranslation = new BodyTypeTranslation(3, "en-GB", "mesomorph");
		BodyTypeTranslation mesoPlTranslation = new BodyTypeTranslation(7, "pl-PL", "mezomorficzny");
		meso.setBodyTypeId(3);
		meso.setBodyTypeTranslations(Arrays.asList(mesoEngTranslation, mesoPlTranslation));
		
		BodyType ecto = new BodyType();
		BodyTypeTranslation ectoEngTranslation = new BodyTypeTranslation(2, "en-GB", "ectomorph");
		BodyTypeTranslation ectoPlTranslation = new BodyTypeTranslation(6, "pl-PL", "ektomorficzny");
		ecto.setBodyTypeId(2);
		ecto.setBodyTypeTranslations(Arrays.asList(ectoEngTranslation, ectoPlTranslation));
		
		BodyType endo = new BodyType();
		BodyTypeTranslation endoEngTranslation = new BodyTypeTranslation(4, "en-GB", "endomorph");
		BodyTypeTranslation endoPlTranslation = new BodyTypeTranslation(8, "pl-PL", "endomorficzny");
		endo.setBodyTypeId(4);
		endo.setBodyTypeTranslations(Arrays.asList(endoEngTranslation, endoPlTranslation));
		
		String str1 = "<[BodyType [bodyTypeId=1, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=1, language=en-GB, isDefault=true, translatedDescription=none], BodyTypeTranslations [btTranslationId=5, language=pl-PL, isDefault=false, translatedDescription=nieokreslono]], description=null], BodyType [bodyTypeId=2, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=2, language=en-GB, isDefault=true, translatedDescription=ectomorph], BodyTypeTranslations [btTranslationId=6, language=pl-PL, isDefault=false, translatedDescription=ektomorficzny]], description=null], BodyType [bodyTypeId=3, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=3, language=en-GB, isDefault=true, translatedDescription=mesomorph], BodyTypeTranslations [btTranslationId=7, language=pl-PL, isDefault=false, translatedDescription=mezomorficzny]], description=null], BodyType [bodyTypeId=4, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=4, language=en-GB, isDefault=true, translatedDescription=endomorph], BodyTypeTranslations [btTranslationId=8, language=pl-PL, isDefault=false, translatedDescription=endomorficzny]], description=null]]>";
		String str2 = "<[BodyType [bodyTypeId=1, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=1, language=en-GB, isDefault=true, translatedDescription=none], BodyTypeTranslations [btTranslationId=5, language=pl-PL, isDefault=false, translatedDescription=nieokreslono]], description=null], BodyType [bodyTypeId=2, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=2, language=en-GB, isDefault=true, translatedDescription=ectomorph], BodyTypeTranslations [btTranslationId=6, language=pl-PL, isDefault=false, translatedDescription=ektomorficzny]], description=null], BodyType [bodyTypeId=3, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=3, language=en-GB, isDefault=true, translatedDescription=mesomorph], BodyTypeTranslations [btTranslationId=7, language=pl-PL, isDefault=false, translatedDescription=mezomorficzny]], description=null], BodyType [bodyTypeId=4, bodyTypeTranslations=[BodyTypeTranslations [btTranslationId=4, language=en-GB, isDefault=true, translatedDescription=endomorph], BodyTypeTranslations [btTranslationId=8, language=pl-PL, isDefault=false, translatedDescription=endomorficzny]], description=null]]>";
		
		System.out.println(str1.equals(str2));
		
		assertThat(Arrays.asList(none, ecto, meso, endo), is(equalTo(bodyTypeDao.getAll())));
	}
	
	@DirtiesContext
	@Test
	public void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
		
		
		super.whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(bodyTypeDao);
	}
	
	@Test
	public void whenFindById2_ThenReturnBodyTypeEctomorphic() throws Exception {
		BodyType ecto = new BodyType();
		BodyTypeTranslation ectoEngTranslation = new BodyTypeTranslation(2, "en-GB", "ectomorph");
		BodyTypeTranslation ectoPlTranslation = new BodyTypeTranslation(6, "pl-PL", "ektomorficzny");
		ecto.setBodyTypeId(2);
		ecto.setBodyTypeTranslations(Arrays.asList(ectoEngTranslation, ectoPlTranslation));
		
		assertThat(ecto, equalTo(super.whenFindById_ThenReturnEntity(bodyTypeDao, 2)));
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
