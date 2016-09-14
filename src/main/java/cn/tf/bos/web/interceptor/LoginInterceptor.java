package cn.tf.bos.web.interceptor;


import org.apache.struts2.ServletActionContext;
import cn.tf.bos.domain.user.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//从session中获取用户登录信息
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			
			//没有登录时设置错误提示信息
	/*		ActionSupport  action=(ActionSupport) invocation.getAction();
			action.addActionError("登录信息已超时，请重新登录");*/
			return "login";
		}else{
			return invocation.invoke();			
		}
	}

}
