package cn.tf.bos.service.impl.bc;


import java.util.List;
import java.util.Random;

import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.bc.RegionService;
import cn.tf.bos.service.bc.StaffService;
import cn.tf.bos.service.bc.SubareaService;


public class SubareaServiceImpl extends BaseService  implements SubareaService{

	@Override
	public void saveOrUpdate(Subarea subarea) {
		
		subareaDao.saveOrUpdate(subarea);
		
	}

	@Override
	public PageResponseBean findByPage(PageRequestBean pageRequestBean) {
		
		return pageQuery(pageRequestBean, subareaDao);
	}

	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			Subarea subarea=subareaDao.findById(id);
			subareaDao.delete(subarea);
		}
		
	}

	@Override
	public List<Subarea> findnoassoriations() {
		return subareaDao.findByNameQuery("Subarea.findnoassoriations");
	}

	@Override
	public List<Subarea> findById(String id) {

		return subareaDao.findByNameQuery("subarea.findById", id);
	}

	
}
