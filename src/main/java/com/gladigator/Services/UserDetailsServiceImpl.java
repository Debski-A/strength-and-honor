package com.gladigator.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Translation;
import com.gladigator.Entities.Translationable;
import com.gladigator.Entities.Sex;
import com.gladigator.Entities.SexTranslation;
import com.gladigator.Exceptions.ServiceException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private BodyTypeDao bodyTypeDao;
	
	@Autowired
	private SexDao sexDao;
	
	@Autowired
	private FrequencyOfActivityDao foaDao;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, List<?>> getSelectiveDetailsAsMap(Locale locale) {
		Map<String, List<?>> listOfSelectives = null;
		try {
			listOfSelectives = new HashMap<>();
			List<BodyType> btList = bodyTypeDao.getAll();
			List<? extends Translationable<Translation>> sList = (List<? extends Translationable<Translation>>) sexDao.getAll();
			setTranslationAccordingToLocale(sList, locale);
			List<FrequencyOfActivity> foaList = foaDao.getAll();
			
			listOfSelectives.put("bodyTypeListOfSelectives", btList);
			listOfSelectives.put("sexListOfSelectives", sList);
			listOfSelectives.put("frequenciesListOfSelectives", foaList);
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
		
		return listOfSelectives;
	}
	
	private void setTranslationAccordingToLocale(List<? extends Translationable<Translation>> list, Locale locale) {
		for (Translationable<Translation> entity : list) {
			entity.setDescription(entity.getTranslations()
					.stream()
					.filter(e -> locale.toLanguageTag().equals(e.getLanguage()))
					.findFirst()
					.orElse(entity.getTranslations()
							.stream()
							.filter(e -> e.getIsDefault())
							.findFirst()
							.get())
					.getTranslatedDescription());
		}
	}
}
