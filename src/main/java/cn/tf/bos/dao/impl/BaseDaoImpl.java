package cn.tf.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.bc.Standard;

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


	//查询总记录数
	public long findTotal(DetachedCriteria detachedCriteria) {
		//投影
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list=this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
		return list.get(0);
	}


	public List<T> findByPage(DetachedCriteria detachedCriteria,
			int firstResult, int maxResult) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResult);
	}



	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
		
	}
  
}
