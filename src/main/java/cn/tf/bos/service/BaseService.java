package cn.tf.bos.service;

import javax.annotation.Resource;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.user.User;

public abstract class BaseService {
	@Resource(name="userDao")
	protected BaseDao<User> userDao ;
		
	
}
