package cn.tf.bos.web.action.user;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.web.action.BaseAction;

public class LoginAction  extends  BaseAction  implements ModelDriven<User> {

	private User user=new User();
	
	public User getModel() {
		return user;
	}
	
	//登录
	public String execute(){
		//判断验证码是否一致
		String checkCodeSession=(String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (checkCodeSession == null || !checkCodeSession.equals(checkcode)) {
			this.addActionError("验证码错误");
			return INPUT;
		}
		//验证成功
		User loginUser=userService.login(user);
		if(loginUser==null){
			this.addActionError("用户名或密码错误");
			return INPUT;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
		}
		return SUCCESS;
	}
	
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	


}
