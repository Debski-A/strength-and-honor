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
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Translation;
import com.gladigator.Entities.Translationable;
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
			List<?> btList = (List<?>) bodyTypeDao.getAll();
			setTranslationAccordingToLocale((List<? extends Translationable<Translation>>) btList, locale);
			List<?> sList = (List<?>) sexDao.getAll();
			setTranslationAccordingToLocale((List<? extends Translationable<Translation>>) sList, locale);
			List<?> foaList = (List<?>) foaDao.getAll();
			setTranslationAccordingToLocale((List<? extends Translationable<Translation>>) foaList, locale);
			
			listOfSelectives.put("bodyTypeListOfSelectives", btList);
			listOfSelectives.put("sexListOfSelectives", sList);
			listOfSelectives.put("frequenciesListOfSelectives", foaList);
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
		return listOfSelectives;
	}
	
	@Override
	public void setTranslationAccordingToLocale(List<? extends Translationable<Translation>> list, Locale locale) {
		for (Translationable<Translation> entity : list) {
			entity.setTranslatedContent(entity.getTranslations()
					.stream()
					.filter(e -> locale.toLanguageTag().equals(e.getLanguage()))
					.findFirst()
					.get()
					.getTranslatedContent());
		}
	}
}
