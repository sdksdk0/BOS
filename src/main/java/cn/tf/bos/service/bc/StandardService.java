package cn.tf.bos.service.bc;

import cn.tf.bos.domain.PageRequestBean;
import cn.tf.bos.domain.PageResponseBean;
import cn.tf.bos.domain.bc.Standard;

public interface StandardService {

	void saveOrUpdate(Standard standard);
	//分页查询
	PageResponseBean fingByPage(PageRequestBean pageRequestBean);
	//批量删除
	void delete(String[] ids);

	
}
