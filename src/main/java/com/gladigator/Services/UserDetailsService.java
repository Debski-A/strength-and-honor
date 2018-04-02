package com.gladigator.Services;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface UserDetailsService {
	
	public Map<String, List<?>> getSelectiveDetailsAsMap(Locale locale);

}
