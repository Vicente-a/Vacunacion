package com.vacunas.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseGenericDAO<T, K extends Serializable> {

	T create(T entity);

	T update(T entity);

	void delete(T entity);
	
	T findById(K key);
	
	List<T> findAll();
	
	List<T> findAllOrderBy(String orderBy);
	
	List<T> findByFk(String cadena);
	
	T findOneByWhere(String cadena);
	
	List<T> query(String cadena);
}
