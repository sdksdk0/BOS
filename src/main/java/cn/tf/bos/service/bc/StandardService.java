package cn.tf.bos.service.bc;

import java.util.List;

import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;

public interface StandardService {

	void saveOrUpdate(Standard standard);
	//分页查询
	PageResponseBean fingByPage(PageRequestBean pageRequestBean);
	//批量删除
	void delete(String[] ids);
	//获取标准列表
	List<Standard> ajaxlist();

	
}
