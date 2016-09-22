package cn.tf.bos.web.action.auth;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.tf.bos.domain.auth.Function;
import cn.tf.bos.web.action.BaseAction;

public class FunctionAction  extends BaseAction  implements ModelDriven<Function>{

	private Function function=new Function();
	
	
	@Override
	public Function getModel() {
		return function;
	}
	
	//父功能点
	public  String ajaxlist(){
		List<Function>  list=functionService.listAll();	
		ActionContext.getContext().put("list", list);
		return "ajaxlist";
	}
	
	public String saveOrUpdate(){
		functionService.saveOrUpdate(function);
		return "saveOrUpdate";
	}
	
	
	public String list(){
		List<Function>  list=functionService.listAll();	
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	//查询授权树
	public String treedata(){
		DetachedCriteria  detachedCriteria=DetachedCriteria.forClass(Function.class);
		detachedCriteria.addOrder(Order.asc("zindex"));
		List<Function>  list=functionService.findTreeData(detachedCriteria);	
		ActionContext.getContext().put("list", list);
		
		return "treedata";
	}
	
	

}
