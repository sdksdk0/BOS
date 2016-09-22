package cn.tf.bos.service.impl.auth;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.tf.bos.domain.auth.Function;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.auth.FunctionService;

public class FunctionServiceImpl extends  BaseService   implements  FunctionService{

	@Override
	public List<Function> listAll() {
		
		return functionDao.findAll();
	}

	@Override
	public void saveOrUpdate(Function function) {
		if(function.getParentFunction()!=null && function.getParentFunction().getId()!=null && function.getParentFunction().getId().trim().length()==0){
			function.setParentFunction(null);
		}
		
		functionDao.save(function);
		
	}

	@Override
	public List<Function> findTreeData(DetachedCriteria detachedCriteria) {
		
		return functionDao.findByCriteria(detachedCriteria);
	}



}
