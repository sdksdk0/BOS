package cn.tf.bos.service.impl.bc;


import java.util.List;

import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.bc.StaffService;


public class StaffServiceImpl extends BaseService  implements StaffService{

	public void saveOrUpdate(Staff staff) {
		staffDao.saveOrUpdate(staff);
		
	}

	public PageResponseBean findByPage(PageRequestBean pgBean) {
		
		return pageQuery(pgBean, staffDao);
	}

	public void delete(String[] ids,String value) {
		//修改id对应的deltag为1
		
		if(value=="1"){
			//说明是作废
			for(String id:ids){
				Staff staff=staffDao.findById(id);
				staff.setDeltag("1");
			}
			
		}else{
			//还原
			for(String id:ids){
				Staff staff=staffDao.findById(id);
				staff.setDeltag("0");
			}
			
		}
		
	
		
	}


}
