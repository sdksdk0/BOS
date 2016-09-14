package cn.tf.bos.web.action.bc;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

//标准管理
public class StandardAction extends BaseAction  implements ModelDriven<Standard>{
	
	private Standard standard=new Standard();
	
	public Standard getModel() {
		return standard;
	}
	
	public String save(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		standard.setUser(user);
		standard.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		
		
		standardService.saveOrUpdate(standard);
		
		return "save";
	}
	
	public String findByPage(){
		PageRequestBean  pageRequestBean=new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		DetachedCriteria  detachedCriteria=DetachedCriteria.forClass(Standard.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		pageRequestBean.setDetachedCriteria(detachedCriteria);
		
		PageResponseBean  pageResponseBean=standardService.fingByPage(pageRequestBean);
		
		//将结果数据转换为json数据
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
			
		return "findByPage";
	}
	
	public String delete(){
		
		String[] ids=standard.getId().split(", ");
		standardService.delete(ids);
		return "delete";
	}
	
	//查询取派标准列表
	public String ajaxlist(){
		//查询列表
		List<Standard>  standardlist=standardService.ajaxlist();
		
		ActionContext.getContext().put("standardlist",standardlist);
		
		return "ajaxlist";
	}
	
	
	
	
	//属性驱动
	private int page;
	private int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	

}
