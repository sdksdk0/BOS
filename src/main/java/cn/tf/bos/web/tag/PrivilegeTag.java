package cn.tf.bos.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.struts2.ServletActionContext;

import cn.tf.bos.domain.user.User;
import cn.tf.bos.utils.PrivilegeUtil;

//自定义标签类
public class PrivilegeTag extends SimpleTagSupport{
	
	private String value;
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(PrivilegeUtil.checkHasPrivailege(user, value)){
			this.getJspBody().invoke(null);
		}
	}

}
