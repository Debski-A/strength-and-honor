package com.gladigator.integrationTests.Daos;

import static org.hamcrest.CoreMatchers.equalTo;
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
		BodyTypeTranslation noneEngTranslation = new BodyTypeTranslation(1, "en-UK", true, "none");
		BodyTypeTranslation nonePlTranslation = new BodyTypeTranslation(5, "pl-PL", false, "nieokreslono");
		none.setBodyTypeId(1);
		none.setBodyTypeTranslations(Arrays.asList(noneEngTranslation, nonePlTranslation));
		
		BodyType meso = new BodyType();
		BodyTypeTranslation mesoEngTranslation = new BodyTypeTranslation(3, "en-UK", true, "mesomorph");
		BodyTypeTranslation mesoPlTranslation = new BodyTypeTranslation(7, "pl-PL", false, "mezomorficzny");
		meso.setBodyTypeId(3);
		meso.setBodyTypeTranslations(Arrays.asList(mesoEngTranslation, mesoPlTranslation));
		
		BodyType ecto = new BodyType();
		BodyTypeTranslation ectoEngTranslation = new BodyTypeTranslation(2, "en-UK", true, "ectomorph");
		BodyTypeTranslation ectoPlTranslation = new BodyTypeTranslation(6, "pl-PL", false, "ektomorficzny");
		ecto.setBodyTypeId(2);
		ecto.setBodyTypeTranslations(Arrays.asList(ectoEngTranslation, ectoPlTranslation));
		
		BodyType endo = new BodyType();
		BodyTypeTranslation endoEngTranslation = new BodyTypeTranslation(4, "en-UK", true, "endomorph");
		BodyTypeTranslation endoPlTranslation = new BodyTypeTranslation(8, "pl-PL", false, "endomorficzny");
		endo.setBodyTypeId(4);
		endo.setBodyTypeTranslations(Arrays.asList(endoEngTranslation, endoPlTranslation));
		
		String str1 = "<[BodyType [bodyTypeId=1, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=1, language=en-UK, isDefault=true, translatedDescription=none], BodyTypeTranslations [sexTranslationId=5, language=pl-PL, isDefault=false, translatedDescription=nieokreślono]], description=null], BodyType [bodyTypeId=2, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=2, language=en-UK, isDefault=true, translatedDescription=ectomorph], BodyTypeTranslations [sexTranslationId=6, language=pl-PL, isDefault=false, translatedDescription=ektomorficzny]], description=null], BodyType [bodyTypeId=3, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=3, language=en-UK, isDefault=true, translatedDescription=mesomorph], BodyTypeTranslations [sexTranslationId=7, language=pl-PL, isDefault=false, translatedDescription=mezomorficzny]], description=null], BodyType [bodyTypeId=4, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=4, language=en-UK, isDefault=true, translatedDescription=endomorph], BodyTypeTranslations [sexTranslationId=8, language=pl-PL, isDefault=false, translatedDescription=endomorficzny]], description=null]]>";
		String str2 = "<[BodyType [bodyTypeId=1, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=1, language=en-UK, isDefault=true, translatedDescription=none], BodyTypeTranslations [sexTranslationId=5, language=pl-PL, isDefault=false, translatedDescription=nieokreślono]], description=null], BodyType [bodyTypeId=2, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=2, language=en-UK, isDefault=true, translatedDescription=ectomorph], BodyTypeTranslations [sexTranslationId=6, language=pl-PL, isDefault=false, translatedDescription=ektomorficzny]], description=null], BodyType [bodyTypeId=3, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=3, language=en-UK, isDefault=true, translatedDescription=mesomorph], BodyTypeTranslations [sexTranslationId=7, language=pl-PL, isDefault=false, translatedDescription=mezomorficzny]], description=null], BodyType [bodyTypeId=4, bodyTypeTranslations=[BodyTypeTranslations [sexTranslationId=4, language=en-UK, isDefault=true, translatedDescription=endomorph], BodyTypeTranslations [sexTranslationId=8, language=pl-PL, isDefault=false, translatedDescription=endomorficzny]], description=null]]>";
		
		System.out.println(str1.equals(str2));
		
		assertThat(bodyTypeDao.getAll(), equalTo(Arrays.asList(none, ecto, meso, endo)));
	}
	
	@DirtiesContext
	@Test
	public void whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException() throws Exception {
		
		
		super.whenGetAll_AndAnyExceptionOccurs_ThenThrowRepositoryException(bodyTypeDao);
	}
	
	@Test
	public void whenFindById2_ThenReturnBodyTypeEctomorphic() throws Exception {
		BodyType ecto = new BodyType();
		BodyTypeTranslation ectoEngTranslation = new BodyTypeTranslation(2, "en-UK", true, "ectomorph");
		BodyTypeTranslation ectoPlTranslation = new BodyTypeTranslation(6, "pl-PL", false, "ektomorficzny");
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
