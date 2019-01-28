package com.gladigator.Services;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.gladigator.Entities.Translation;
import com.gladigator.Entities.Translationable;

public interface UserDetailsService {
	
	public Map<String, List<?>> getSelectiveDetailsAsMap(Locale locale);
	public void setTranslationAccordingToLocale(List<? extends Translationable<Translation>> list, Locale locale);
}
