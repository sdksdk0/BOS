package cn.tf.bos.service.impl.user;

import java.util.List;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.user.UserService;
import cn.tf.bos.utils.MD5Utils;

public class UserServiceImpl extends BaseService implements UserService{

	public User login(User user) {
		List<User>  list= userDao.findByNameQuery("User.login", user.getUsername(),MD5Utils.md5(user.getPassword()));
		return list.isEmpty()?null:list.get(0);
	}

}
