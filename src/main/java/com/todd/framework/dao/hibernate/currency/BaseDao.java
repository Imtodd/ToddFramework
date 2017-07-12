package com.todd.framework.dao.hibernate.currency;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;



public class BaseDao<T> implements IBaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	/**
	 * 无参构造
	 */
	public BaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	/**
	 * 返回session
	 * 
	 * @return
	 */
	public Session getsession() {
		return sessionFactory.getCurrentSession();
	}

	public void insert(T entity) {
		this.getsession().save(entity);
	}

	public SessionFactory getSessionfactory() {
		return sessionFactory;
	}

	public void setSessionfactory(SessionFactory sessionFactory) {
		this.sessionFactory = (SessionFactory) new HibernateTemplate(sessionFactory);
	}

	public void update(T entity) {
		this.getsession().update(entity);
	}

	public void delete(Serializable id) {
		this.getsession().delete(this.findById(id));
	}

	public T findById(Serializable id) {
		return this.getsession().get(this.clazz, id);
	}

	public List<T> findByHQL(String hql, Object... params) {
		Query query = this.getsession().createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}

}
