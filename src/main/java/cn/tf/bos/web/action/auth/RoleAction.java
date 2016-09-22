package cn.tf.bos.web.action.auth;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.auth.Role;
import cn.tf.bos.web.action.BaseAction;

public class RoleAction  extends BaseAction  implements ModelDriven<Role>{

	private Role role=new Role();
	
	
	@Override
	public Role getModel() {
		return role;
	}
	
	public String saveOrUpdate(){
		roleService.saveOrUpdate(role,functionIds);
		
		return "saveOrUpdate";
	}

	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	
	
	//查询所有权限
	public String list(){
		List<Role>  list=roleService.fingAll();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	
	
	
	

}
