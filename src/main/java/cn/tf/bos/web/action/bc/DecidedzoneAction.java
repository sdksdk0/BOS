package cn.tf.bos.web.action.bc;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;
import cn.tf.boscrm.domain.Customer;


//标准管理
public class DecidedzoneAction extends BaseAction  implements ModelDriven<Decidedzone>{
	
private Decidedzone decidedzone=new Decidedzone();
	
	public Decidedzone getModel() {
		return decidedzone;
	}
	
	public String saveOrUpdate(){
		
		decidedzoneService.saveOrUpdate(decidedzone,subareaId);
		return "saveOrUpdate";
	}
	
	private String[] subareaId;
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	
	public String findByPage(){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Decidedzone.class);
		PageRequestBean pageRequestBean=initPageRequestBean(detachedCriteria);
		PageResponseBean pageResponseBean=decidedzoneService.findByPage(pageRequestBean);
		
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "findByPage";
	}
	
	//查询已关联的
	public String findHasAssoctionCustomer(){
		List<Customer>  customers=customerService.findHasAssoctionCustomer(decidedzone.getId());
		ActionContext.getContext().put("customers", customers);
		return "findHasAssoctionCustomer";
	}
	
	//查询未关联的
	public String findNoAssoctionCustomer(){
		List<Customer>  customers=customerService.findNoAssoctionCustomer();
		ActionContext.getContext().put("customers", customers);
		return "findNoAssoctionCustomer";	
	}
	//关联客户到定区
	public String assignedCustomerToDecidedzone(){
		customerService.assignedCustomerToDecidedzone(customerIds, decidedzone.getId());
		return "assignedCustomerToDecidedzone";
	}
	
	private String[] customerIds;
	
	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}
	
	//双击之后在页面下方显示该定区已绑定的客户信息
	public String  findCustomer(){
		
		List<Customer>  customers=customerService.findHasAssoctionCustomer(decidedzone.getId());
		ActionContext.getContext().put("customers", customers);
		
		return "findCustomer";
	}
	
	
	

}
