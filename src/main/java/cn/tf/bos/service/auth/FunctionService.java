package cn.tf.bos.service.auth;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.tf.bos.domain.auth.Function;

public interface FunctionService {

	List<Function> listAll();

	void saveOrUpdate(Function function);

	//查询树节点的数据
	List<Function> findTreeData(DetachedCriteria detachedCriteria);

}
