package cn.tf.bos.service.impl.bc;

import java.util.List;

import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.bc.StandardService;

public class StandardServiceImpl extends BaseService  implements StandardService{

	public void saveOrUpdate(Standard standard) {
		standardDao.saveOrUpdate(standard);	
	}

	public PageResponseBean fingByPage(PageRequestBean pageRequestBean) {
		
		PageResponseBean pageResponseBean=new PageResponseBean();
		//查询满足条件的总记录数
		long total=standardDao.findTotal(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);
		
		//查询当前页显示的数据
		int firstResult=(pageRequestBean.getPage()-1)*pageRequestBean.getRows();
		int maxResult=pageRequestBean.getRows();
		
		pageRequestBean.getDetachedCriteria().setProjection(null);
		List<Standard> data=standardDao.findByPage(pageRequestBean.getDetachedCriteria(),firstResult,maxResult);
		pageResponseBean.setRows(data);
		
		return pageResponseBean;
	}

	public void delete(String[] ids) {
		//更改数据的deltag，并不是真的删除
		for(String id:ids){
			Standard standard=standardDao.findById(id);
			standard.setDeltag("1");
		}
		
	}

	public List<Standard> ajaxlist() {
		return standardDao.findByNameQuery("Standard.ajaxlist");
	}

	
	
}
