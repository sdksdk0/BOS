package cn.tf.bos.service.impl.qp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ExecutionService;

import cn.tf.bos.domain.qp.WorkOrderManage;
import cn.tf.bos.domain.zm.ZhongZhuanInfo;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.qp.WorkOrderManageService;

public class WorkOrderManageServiceImpl  extends BaseService  implements  WorkOrderManageService{

	@Override
	public void saveOrUpdate(WorkOrderManage workOrderManage) {
		workordermanageDao.saveOrUpdate(workOrderManage);
		
	}

	@Override
	public PageResponseBean findByPage(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, workordermanageDao );
	}

	@Override
	public PageResponseBean findByLucene(String conditionName,
			String conditionValue, int page, int rows) {
		
		return workordermanageDao.findByLucene(conditionName,conditionValue,page,rows);
	}

	@Override
	public List<WorkOrderManage> listUnCheck() {
		
		return workordermanageDao.findByNameQuery("WorkOrderManage.listUnCheck");
	}

	@Override
	public void check(WorkOrderManage workOrderManage) {
		//将字段从0改为1
		WorkOrderManage  exist=workordermanageDao.findById(workOrderManage.getId());
		exist.setManagerCheck("1");
		
		//启动流程
		ExecutionService executionService=processEngine.getExecutionService();
		//在启动流程时关联流程实例全局的中转信息对象
		
		ZhongZhuanInfo zhongzhuanInfo=new ZhongZhuanInfo();
		//未到达
		zhongzhuanInfo.setArrive("0");
		//关联工单信息
		zhongzhuanInfo.setWorkOrderManage(exist);  
		//持久化
		zhongzhuanDao.save(zhongzhuanInfo);
		
		Map<String,Object>  variables=new HashMap<String,Object>();
		variables.put("zhongzhuanInfo", zhongzhuanInfo);
		
		executionService.startProcessInstanceByKey("transfer",variables);
		
	}
		
}
