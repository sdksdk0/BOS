package cn.tf.bos.service.user;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageQuery;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;

public interface UserService extends PageQuery{
	//用户登录
	User login(User user);
	
	
	//修改密码
	void editpwd(User user);


	void saveOrUpdate(User user);

	//授予角色
	void grantRole(User user);


	
	

}
