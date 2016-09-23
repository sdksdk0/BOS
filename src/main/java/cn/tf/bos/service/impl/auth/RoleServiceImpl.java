package cn.tf.bos.service.impl.auth;

import java.util.List;

import org.jbpm.api.IdentityService;

import cn.tf.bos.domain.auth.Function;
import cn.tf.bos.domain.auth.Role;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.auth.RoleService;

public class RoleServiceImpl extends BaseService  implements  RoleService{

	@Override
	public void saveOrUpdate(Role role, String functionIds) {
		
		roleDao.saveOrUpdate(role);
		
		//建立role和function联系
		if(functionIds!=null){
			String[] ids=functionIds.split(",");
			for (String id : ids) {
				Function function=functionDao.findById(id);
				role.getFunctions().add(function);
			}
		}
		
		//建立jbpm系统组信息
		IdentityService  identityService=processEngine.getIdentityService();
		identityService.createGroup(role.getName());
		
		
	}

	@Override
	public List<Role> fingAll() {
		
		return roleDao.findAll();
	}
}
