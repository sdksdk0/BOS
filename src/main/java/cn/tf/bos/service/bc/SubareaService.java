package cn.tf.bos.service.bc;

import cn.tf.bos.domain.bc.Subarea;
import cn.tf.bos.page.PageQuery;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;



public interface SubareaService extends PageQuery{

	void saveOrUpdate(Subarea subarea);

	void delete(String[] ids);

	



}
