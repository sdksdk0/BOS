package cn.tf.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.filter.impl.FullTextFilterImpl;
import org.hibernate.search.impl.FullTextSessionImpl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageResponseBean;

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
		List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 1);
		return list.get(0);
	}


	public List<T> findByPage(DetachedCriteria detachedCriteria,
			int firstResult, int maxResult) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResult);
	}



	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
		
	}


	//luence查询
	@Override
	public PageResponseBean findByLucene(String conditionName,
			String conditionValue, int page, int rows) {
		Session session=this.getSession();
		FullTextSession  fullTextSession=new FullTextSessionImpl(session);
		
		Query query=new WildcardQuery(new Term(conditionName,"*"+conditionValue+"*"));
		
		//获得全文检索的query
		FullTextQuery  fullTextQuery=fullTextSession.createFullTextQuery(query);
		PageResponseBean  pageResponseBean=new PageResponseBean();
		pageResponseBean.setTotal(fullTextQuery.getResultSize());
		
		//当前页数据
		int firstResult=(page-1)*rows;
		int maxResults=rows;
		List  list=fullTextQuery.setFirstResult(firstResult).setMaxResults(maxResults).list();
		pageResponseBean.setRows(list);
		
		return pageResponseBean;
	}
  
}
