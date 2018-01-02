package com.gladigator.Services;

public interface GenericService<T> {
	
	public T findById(Integer id);
	public void saveOrUpdate(T entity);

}
