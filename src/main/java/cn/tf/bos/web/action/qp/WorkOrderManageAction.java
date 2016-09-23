package cn.tf.bos.web.action.qp;

import java.util.HashMap;
import java.util.List;
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
		
		if(conditionName!=null && conditionName.trim().length()>0 && conditionValue!=null && conditionValue.trim().length()>0){
			//有条件查询
			PageResponseBean pageResponseBean=workordermanagerService.findByLucene(conditionName,conditionValue,page,rows);
			ActionContext.getContext().put("pageResponseBean", pageResponseBean);
			
		}else{
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(WorkOrderManage.class);
			PageRequestBean  pageRequestBean=initPageRequestBean(detachedCriteria);
			
			PageResponseBean pageResponseBean=workordermanagerService.findByPage(pageRequestBean);
			
			ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		}
		return "findByPage";
	}
	
	private String conditionName;
	private String conditionValue;

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	
	//查询所有未审核的工作单
	public String list(){
		List<WorkOrderManage>  workOrderManages=workordermanagerService.listUnCheck();
		ActionContext.getContext().put("workOrderManages", workOrderManages);
		return "list";
	}
	
	//通过审核
	public String check(){
		workordermanagerService.check(workOrderManage);
		
		return "check";
	}
	

}
