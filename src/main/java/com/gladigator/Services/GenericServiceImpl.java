package com.gladigator.Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gladigator.Daos.GenericDao;
import com.gladigator.Exceptions.ServiceException;

public abstract class GenericServiceImpl<T> implements GenericService<T> {
	
	private static final Logger LOG = LogManager.getLogger(GenericServiceImpl.class);
	
	@Autowired
	private GenericDao<T> dao;
	
	@Transactional
	@Override
	public T findById(Integer id) {
		LOG.debug("ID parameter = {}", id);
		T entity = null;
		try {
			entity = dao.findById(id);
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
		return entity;
	}
	
	@Transactional
	@Override
	public void saveOrUpdate(T entity) {
		LOG.debug("Entity parameter = {}", entity);
		try {
			dao.saveOrUpdate(entity);
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
	}
	
	@Transactional
	@Override
	public List<T> getAll() {
		List<T> listOfEntities = null;
		try {
			listOfEntities = dao.getAll();
		} catch (Exception ex) {
			throw new ServiceException("An Exception occurred", ex);
		}
		return listOfEntities;
	}

}
