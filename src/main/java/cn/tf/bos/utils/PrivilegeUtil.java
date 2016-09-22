package cn.tf.bos.utils;

import java.util.Set;

import cn.tf.bos.domain.auth.Function;
import cn.tf.bos.domain.auth.Role;
import cn.tf.bos.domain.user.User;
//判断用户是否具有权限
public class PrivilegeUtil {

	public static boolean checkHasPrivailege(User user, String needPrivilege) {
		
		if(user.getUsername().equals("admin")){
			return true;
		}
		
		Role role=user.getRole();
		if(role==null){
			//没有角色，没有权限
			return false;
		}else{
			Set<Function>  functions=role.getFunctions();
			for (Function function : functions) {
				if(function.getName().equals(needPrivilege)){
					return true;	 //满足权限
				}	
			}
			return false;
		}
	
	}

}
