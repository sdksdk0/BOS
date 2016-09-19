package cn.tf.bos.service.impl.qp;

import cn.tf.bos.domain.qp.WorkOrderManage;
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
		
}
