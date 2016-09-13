package cn.tf.bos.service.user;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.service.BaseService;

public interface UserService {
	//用户登录
	User login(User user);
	
	
	//修改密码
	void editpwd(User user);
	
	

}
