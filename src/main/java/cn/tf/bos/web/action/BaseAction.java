package cn.tf.bos.web.action;

import javax.annotation.Resource;

import cn.tf.bos.service.user.UserService;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction  extends ActionSupport  {
	@Resource(name="userService")
	protected UserService userService;

}
