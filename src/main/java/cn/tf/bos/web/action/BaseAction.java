package cn.tf.bos.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.jbpm.api.ProcessEngine;

import cn.tf.bos.domain.qp.WorkOrderManage;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.service.auth.FunctionService;
import cn.tf.bos.service.auth.RoleService;
import cn.tf.bos.service.bc.DecidedzoneService;
import cn.tf.bos.service.bc.RegionService;
import cn.tf.bos.service.bc.StaffService;
import cn.tf.bos.service.bc.StandardService;
import cn.tf.bos.service.bc.SubareaService;
import cn.tf.bos.service.qp.NoticeBillService;
import cn.tf.bos.service.qp.WorkOrderManageService;
import cn.tf.bos.service.user.UserService;
import cn.tf.bos.service.workflow.BosTaskSerice;
import cn.tf.boscrm.service.CustomerService;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction  extends ActionSupport  {
	@Resource(name="userService")
	protected UserService userService;

	
	@Resource(name="standardService")
	protected StandardService standardService;
	
	
	@Resource(name="staffService")
	protected StaffService staffService;
	
	
	@Resource(name = "regionService")
	protected RegionService regionService;
	
	
	@Resource(name = "subareaService")
	protected SubareaService subareaService;
	
	
	@Resource(name = "decidedzoneService")
	protected DecidedzoneService decidedzoneService;
	
	
	@Resource(name = "customerService")
	protected CustomerService customerService;
	
	@Resource(name = "noticebillService")
	protected NoticeBillService noticebillService;
	
	@Resource(name = "workordermanagerService")
	protected WorkOrderManageService  workordermanagerService;
	
	
	@Resource(name = "functionService")
	protected FunctionService  functionService;
	
	@Resource(name = "roleService")
	protected RoleService  roleService;
	
	@Resource(name = "processEngine")
	protected ProcessEngine processEngine;
	
	@Resource(name = "bosTaskService")
	protected BosTaskSerice  bosTaskService;
	
	
	protected  int page;
	protected  int rows;

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	// 封装PageRequestBean
		public PageRequestBean initPageRequestBean(DetachedCriteria detachedCriteria) {
			// 将分页查询参数 ，封装 PageRequestBean 对象中
			PageRequestBean pageRequestBean = new PageRequestBean();
			pageRequestBean.setPage(page);
			pageRequestBean.setRows(rows);

			pageRequestBean.setDetachedCriteria(detachedCriteria);
			return pageRequestBean;
		}
	
	
}
