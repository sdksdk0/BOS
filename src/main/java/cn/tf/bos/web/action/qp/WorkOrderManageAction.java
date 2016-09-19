package cn.tf.bos.web.action.qp;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.qp.WorkOrderManage;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;


//标准管理
public class WorkOrderManageAction extends BaseAction  implements ModelDriven<WorkOrderManage>{
	
private WorkOrderManage workOrderManage=new  WorkOrderManage();
	
	public WorkOrderManage getModel() {
		return workOrderManage;
	}
	
	public String saveOrUpdate(){
		
		try {
			workordermanagerService.saveOrUpdate(workOrderManage);
		
			Map<String,Object>  map=new HashMap<String,Object>();
			map.put("result", "success");
			map.put("msg", "保存成功");
			ActionContext.getContext().put("map", map);
		} catch (Exception e) {
			Map<String,Object>  map=new HashMap<String,Object>();
			map.put("result", "failure");
			map.put("msg", "保存失败,"+e.getMessage());
			ActionContext.getContext().put("map", map);
		}
		
		return "saveOrUpdate";
	}
	
	//分页查询
	public String findByPage(){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(WorkOrderManage.class);
		PageRequestBean  pageRequestBean=initPageRequestBean(detachedCriteria);
		
		PageResponseBean pageResponseBean=workordermanagerService.findByPage(pageRequestBean);
		
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "findByPage";
	}
	
	

}
