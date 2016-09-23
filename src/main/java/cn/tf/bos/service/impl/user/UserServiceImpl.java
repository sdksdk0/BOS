package cn.tf.bos.service.impl.user;

import java.util.List;

import org.hibernate.Hibernate;
import org.jbpm.api.IdentityService;
import org.jbpm.api.identity.Group;

import cn.tf.bos.domain.auth.Role;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.user.UserService;
import cn.tf.bos.utils.MD5Utils;

public class UserServiceImpl extends BaseService implements UserService{

	public User login(User user) {
		List<User>  list= userDao.findByNameQuery("User.login", user.getUsername(),MD5Utils.md5(user.getPassword()));
		User loginUser=list.isEmpty()?null:list.get(0);
		//对用户关联的权限信息初始化
		if(loginUser!=null && loginUser.getRole()!=null){
			Hibernate.initialize(loginUser.getRole().getFunctions());
		}
				
		return loginUser;
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
		
		//在添加用户的同时，向jbpm系统插入一个用户
		IdentityService  identityService=processEngine.getIdentityService();
		identityService.createUser(user.getId(), user.getUsername(),  user.getUsername());
		
		if(user.getRole()!=null){
			//在添加用户时，建立了关系
			 Role role=roleDao.findById(user.getRole().getId());
			identityService.createMembership(user.getId(), role.getName());
		}	
	}

	@Override
	public PageResponseBean findByPage(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, userDao );
	}

	@Override
	public void grantRole(User user) {
		User exist=userDao.findById(user.getId());
		exist.setRole(user.getRole());  //关联角色自动更新
		
		//建立jbpm和组的关系，一个用户只属于一个组
		//先删除原来的关系，再建立新关系
		IdentityService identityService=processEngine.getIdentityService();
		List<Group>  list=identityService.findGroupsByUser(exist.getId());
		for (Group group : list) {
			identityService.deleteMembership(exist.getId(), group.getId(), null);
		}
		Role role=roleDao.findById(user.getRole().getId());
		identityService.createMembership(exist.getId(), role.getName());
	}

}
