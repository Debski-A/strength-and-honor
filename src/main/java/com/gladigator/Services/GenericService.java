package com.gladigator.Services;

import java.util.List;

public interface GenericService<T> {
	
	public T findById(Integer id);
	public void saveOrUpdate(T entity);
	public List<T> getAll();

}
