package cn.tf.bos.web.action.qp;


import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.domain.qp.NoticeBill;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;
import cn.tf.boscrm.domain.Customer;


//标准管理
public class NoticeBillAction extends BaseAction  implements ModelDriven<NoticeBill>{
	
private NoticeBill noticeBill=new NoticeBill();
	
	public NoticeBill getModel() {
		return noticeBill;
	}
	
	public String save(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		noticeBill.setUser(user);
		
		noticebillService.save(noticeBill);
		
		return "save";
	}

}
