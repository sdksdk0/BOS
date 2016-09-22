package cn.tf.bos.web.interceptor;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import cn.tf.bos.annotation.Privilege;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.utils.PrivilegeUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//权限控制拦截器
public class PrivilegeInterceptor  extends  AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//判断目标action的业务方法上是否有privailege注解
		Class c=invocation.getAction().getClass(); 
		//目标业务方法名称
		String methodName=invocation.getProxy().getMethod();
		Method method=c.getDeclaredMethod(methodName);
		if(method.isAnnotationPresent(Privilege.class)){
			Privilege privilege=method.getAnnotation(Privilege.class);
			String needPrivilege=privilege.value();
			
			User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
			if(PrivilegeUtil.checkHasPrivailege(user,needPrivilege)){
				//有权限
				return invocation.invoke();
			}else{
				//没有权限
				return "noprivilege";
			}
		}else{
			//无注解，不需要权限
			return invocation.invoke();
		}
	}

}
