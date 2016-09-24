package cn.tf.bos.service;

import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.auth.Function;
import cn.tf.bos.domain.auth.Role;
import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.domain.qp.NoticeBill;
import cn.tf.bos.domain.qp.WorkBill;
import cn.tf.bos.domain.qp.WorkOrderManage;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.domain.zm.InStore;
import cn.tf.bos.domain.zm.OutStore;
import cn.tf.bos.domain.zm.ReceiveGoodsInfo;
import cn.tf.bos.domain.zm.TransferInfo;
import cn.tf.bos.domain.zm.ZhongZhuanInfo;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.boscrm.service.CustomerService;

public abstract class BaseService {
	@Resource(name="userDao")
	protected BaseDao<User> userDao ;
		
	@Resource(name="standardDao")
	protected BaseDao<Standard>  standardDao;
	
	
	@Resource(name="staffDao")
	protected BaseDao<Staff>  staffDao;
	
	
	@Resource(name="regionDao")
	protected BaseDao<Region>  regionDao;
	
	
	@Resource(name="subareaDao")
	protected BaseDao<Subarea>  subareaDao;
	
	@Resource(name="decidedzoneDao")
	protected BaseDao<Decidedzone>  decidedzoneDao;
	
	
	@Resource(name="noticebillDao")
	protected BaseDao<NoticeBill>  noticebillDao;
	
	@Resource(name="workbillDao")
	protected BaseDao<WorkBill>  workbillDao;
	
	@Resource(name = "customerService")
	protected CustomerService customerService;
	
	
	@Resource(name="workordermanageDao")
	protected BaseDao<WorkOrderManage>  workordermanageDao;
	
	
	@Resource(name="functionDao")
	protected BaseDao<Function>  functionDao;
	
	@Resource(name="roleDao")
	protected BaseDao<Role>  roleDao;
	
	@Resource(name = "processEngine")
	protected ProcessEngine processEngine;
	

	@Resource(name="zhongzhuanDao")
	protected BaseDao<ZhongZhuanInfo>  zhongzhuanDao;
	
	@Resource(name="transferInfoDao")
	protected BaseDao<TransferInfo>  transferInfoDao;
	
	
	@Resource(name="inStoreDao")
	protected BaseDao<InStore>  inStoreDao;
	
	@Resource(name="outStoreDao")
	protected BaseDao<OutStore>  outStoreDao;
	
	
	@Resource(name="receiveGoodsInfoDao")
	protected BaseDao<ReceiveGoodsInfo>  receiveGoodsInfoDao;
	
	// 分页通用代码
	public <T> PageResponseBean pageQuery(PageRequestBean pageRequestBean, BaseDao<T> dao) {
		PageResponseBean pageResponseBean = new PageResponseBean();

		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows(); // 　从哪条开始
		int maxResults = pageRequestBean.getRows(); // 返回记录数
		List<T> data = dao.findByPage(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(data);

		// 满足当前条件，记录总条数
		long total = dao.findTotal(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);
		
		return pageResponseBean;
	}
}
