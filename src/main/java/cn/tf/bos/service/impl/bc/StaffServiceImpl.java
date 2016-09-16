package cn.tf.bos.service.impl.bc;


import java.util.List;
import java.util.Random;

import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.bc.StaffService;


public class StaffServiceImpl extends BaseService  implements StaffService{
	
	public void saveOrUpdate(Staff staff) {
		
		if(staff.getId()==null){
			//为其添加主键
			staff.setId("s"+(int)((Math.random()*9+1)*100000000));
		}
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

	@Override
	public List<Staff> findAllNoDelete() {
		
		return staffDao.findByNameQuery("staff.findAllNoDelete");
	}
}
