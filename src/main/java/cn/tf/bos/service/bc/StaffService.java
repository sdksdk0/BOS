package cn.tf.bos.service.bc;

import java.util.List;

import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.page.PageQuery;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;


public interface StaffService extends PageQuery{

	void saveOrUpdate(Staff staff);

	void delete(String[] ids, String value);

	List<Staff> findAllNoDelete();

}
