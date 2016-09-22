package cn.tf.bos.service.impl.user;

import java.util.List;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.user.UserService;
import cn.tf.bos.utils.MD5Utils;

public class UserServiceImpl extends BaseService implements UserService{

	public User login(User user) {
		List<User>  list= userDao.findByNameQuery("User.login", user.getUsername(),MD5Utils.md5(user.getPassword()));
		return list.isEmpty()?null:list.get(0);
	}

	public void editpwd(User user) {
		User exist=userDao.findById(user.getId());
		exist.setPassword(MD5Utils.md5(user.getPassword()));
	}

	@Override
	public void saveOrUpdate(User user) {
		if(user.getRole()!=null && user.getRole().getId()!=null && user.getRole().getId().trim().length()==0){
			user.setRole(null);
		}
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDao.saveOrUpdate(user);
		
	}

	@Override
	public PageResponseBean findByPage(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, userDao );
	}

	@Override
	public void grantRole(User user) {
		User exist=userDao.findById(user.getId());
		exist.setRole(user.getRole());
		
	}

}
