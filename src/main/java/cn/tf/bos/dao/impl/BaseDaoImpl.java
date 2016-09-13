package cn.tf.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tf.bos.dao.BaseDao;

public class BaseDaoImpl<T>  extends HibernateDaoSupport  implements BaseDao<T>{
	String className;
	public BaseDaoImpl(String className){
		this.className=className;
	}
	
	
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	public T findById(Serializable id) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		return this.getHibernateTemplate().find("from  "+className);
	}

	public List<T> findByNameQuery(String queryName, Object... values) {
		
		return this.getHibernateTemplate().findByNamedQuery(queryName,values);
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
