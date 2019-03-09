package com.gladigator.Daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gladigator.Exceptions.RepositoryException;

public abstract class GenericDaoImpl<T> implements GenericDao<T>{
	
	private static final Logger LOG = LoggerFactory.getLogger(GenericDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> entityClass;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public List<T> getAll() {
		List<T> list = null;
		try {
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(entityClass);
			Root<T> root = query.from(entityClass);
			query.select(root);
			Query<T> q = getSession().createQuery(query);
			list = q.getResultList();
		} catch (Exception ex) {
			throw new RepositoryException("An Exception occurred", ex);
		}
		LOG.debug("List of all {} entities = {}", entityClass.getSimpleName(), list);
		return list;
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
		} catch (Exception ex) {
			throw new RepositoryException("An Exception occurred", ex);
		}
		LOG.info("{} entity has been saved", entityClass.getName());
	}
	
	@Override
	public T findById(Integer id) {
		T entity = null;
		try {
			entity = getSession().get(entityClass, id);
		} catch (Exception ex) {
			throw new RepositoryException("An Exception occurred", ex);
		}
		if (entity == null) throw new RepositoryException("No " + entityClass.getSimpleName() + " entity with such id");
		LOG.debug("{} entity obtained from database = {}", entityClass.getSimpleName(), entity);
		return entity;
	}
	
	

}
