package cn.tf.bos.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;



//自定义异常控制
public class ExceptionInterceptor extends AbstractInterceptor{
	private static final Logger LOGGER = Logger.getLogger(ExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result=null;
		
		try{
			result=invocation.invoke();
			
		}catch(Exception e){
			LOGGER.error("发生异常", e);
			
			// 判断是否为ajax请求
			if (ServletActionContext.getRequest().getHeader("X-Requested-With") != null) {
				// ajax 请求
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("result", "failure");
				map.put("msg", "修改密码失败," + e.getMessage());
				ActionContext.getContext().put("map", map);
				result = "errorjson";
			} else {
				result = "error";
			}
		}
		return result;
		
	}
	
	
	

}
