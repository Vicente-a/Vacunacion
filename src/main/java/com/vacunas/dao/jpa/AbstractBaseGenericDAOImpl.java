package com.vacunas.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.vacunas.dao.BaseGenericDAO;

public abstract class AbstractBaseGenericDAOImpl<T, K extends Serializable>
		implements BaseGenericDAO<T, K> {
	@PersistenceContext
	protected EntityManager em;
	protected String SENTENCIA = "SELECT DISTINCT t FROM ";
	protected Class<T> classType;
	protected StringBuilder query;

	@SuppressWarnings("unchecked")
	public AbstractBaseGenericDAOImpl() {
		super();
		this.classType = (Class<T>) getParameterClass(getClass(), 0);
	}

	private static Class<?> getParameterClass(Class<?> clazz, int index) {
		return (Class<?>) (((ParameterizedType) clazz.getGenericSuperclass())
				.getActualTypeArguments()[index]);
	}

	public T create(T entity) {
		this.em.persist(entity);
		return entity;
	}

	public T update(T entity) {
		return this.em.merge(entity);
	}

	public void delete(T entity) {
		this.em.remove(em.merge(entity));
	}
	
	public T findById(K key) {
		return this.em.find(classType, key);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			Query q = em.createQuery(SENTENCIA + classType.getSimpleName()+ " t ");
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
		

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAllOrderBy(String orderBy) {
		try {
			query = new StringBuilder();
			query.append(SENTENCIA);
			query.append(classType.getSimpleName());
			query.append(" t order by "+orderBy);
			Query q = em.createQuery(query.toString());
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
		

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByFk(String cadena) {
		try {
			query = new StringBuilder();
			query.append(SENTENCIA);
			query.append(classType.getSimpleName());
			query.append(" t "+cadena);
			Query q = em.createQuery(query.toString());
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
		

	}
	
	@SuppressWarnings("unchecked")
	public T findOneByWhere(String cadena) {
		try {
			query = new StringBuilder();
			query.append(SENTENCIA);
			query.append(classType.getSimpleName());
			query.append(" t "+cadena);
			Query q = em.createQuery(query.toString());
			return (T) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		

	}
	
	public List<T> findByFk(String cadena, String tabla) {
		try {
			query = new StringBuilder();
			query.append(SENTENCIA);
			query.append(classType.getSimpleName());
			query.append(" t inner join t.");
			query.append(tabla+" e "+cadena);
			Query q = em.createQuery(query.toString());
			@SuppressWarnings("unchecked")
			List<T> result = q.getResultList();
			return result;
		} catch (Exception e) {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<T> query(String cadena) {
		try {
			Query q = em.createQuery(cadena);
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
		
	}
}
