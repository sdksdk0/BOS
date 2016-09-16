package cn.tf.bos.web.action.bc;


import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;


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
	

}
