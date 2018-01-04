package com.gladigator.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.BodyTypeDao;
import com.gladigator.Daos.FrequencyOfActivityDao;
import com.gladigator.Daos.SexDao;
import com.gladigator.Entities.BodyType;
import com.gladigator.Entities.FrequencyOfActivity;
import com.gladigator.Entities.Sex;
import com.gladigator.Exceptions.ServiceException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private BodyTypeDao bodyTypeDao;
	
	@Autowired
	private SexDao sexDao;
	
	@Autowired
	private FrequencyOfActivityDao foaDao;

	@Transactional
	@Override
	public Map<String, List<?>> getSelectiveDetailsAsMap() {
		Map<String, List<?>> listOfSelectives = null;
		try {
			listOfSelectives = new HashMap<>();
			List<BodyType> btList = bodyTypeDao.getAll();
			List<Sex> sList = sexDao.getAll();
			List<FrequencyOfActivity> foaList = foaDao.getAll();
			
			listOfSelectives.put("bodyTypeListOfSelectives", btList);
			listOfSelectives.put("sexListOfSelectives", sList);
			listOfSelectives.put("frequenciesListOfSelectives", foaList);
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
		
		return listOfSelectives;
	}

}
