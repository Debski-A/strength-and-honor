package com.gladigator.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.GenericDao;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
	
	private static final Logger LOG = LogManager.getLogger(GenericServiceImpl.class);
	
	@Autowired
	private GenericDao<T> dao;
	
	private final Class<T> entityClass;

	public GenericServiceImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional
	@Override
	public T findById(Integer id) {
		return null;
	}
	
	@Transactional
	@Override
	public void saveOrUpdate(T entity) {
		
	}

}
