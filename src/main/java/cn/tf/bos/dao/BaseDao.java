package cn.tf.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageResponseBean;

//通用Dao
public interface  BaseDao<T> {
	//保存
	public void save(T t);
	//更新
	public void update(T t);
	//删除
	public void delete(T t);
	
	//根据id查询
	public T findById(Serializable  serializable);
	
	//查询所有
	public List<T> findAll();
	
	//条件查询
	public  List<T>  findByNameQuery(String queryName,Object...  values);
	
	public List<T>  findByCriteria(DetachedCriteria detachedCriteria);
	
	//记录总数
	public long findTotal(DetachedCriteria detachedCriteria);
	
	//分页查询
	public List<T> findByPage(DetachedCriteria detachedCriteria,
			int firstResult, int maxResult);
	
	public void saveOrUpdate(T t);
	
	//luence查询
	public PageResponseBean findByLucene(String conditionName,
			String conditionValue, int page, int rows);
	
}
