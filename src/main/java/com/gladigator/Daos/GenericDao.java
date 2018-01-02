package com.gladigator.Daos;

import java.util.List;

public interface GenericDao <T>{
	
	public T findById(Integer id);
	public void saveOrUpdate(T entity);
	public List<T> getAll();
	

}
