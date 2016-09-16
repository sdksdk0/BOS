package cn.tf.bos.service.impl.bc;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.bc.DecidedzoneService;




public class DecidedzoneServiceImpl extends BaseService  implements DecidedzoneService{



	@Override
	public void saveOrUpdate(Decidedzone decidedzone, String[] subareaId) {
		//保存定区数据
		decidedzoneDao.save(decidedzone);
		
		//定区和分区关联
		for (String id : subareaId) {
			Subarea subarea=subareaDao.findById(id);
			subarea.setDecidedzone(decidedzone);
			
			
		}
		
	}

	@Override
	public PageResponseBean findByPage(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, decidedzoneDao);
	}

	

	
}
