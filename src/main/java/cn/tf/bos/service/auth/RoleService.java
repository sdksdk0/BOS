package cn.tf.bos.service.auth;

import java.util.List;

import cn.tf.bos.domain.auth.Role;

public interface RoleService {

	void saveOrUpdate(Role role, String functionIds);

	List<Role> fingAll();

}
