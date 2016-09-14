package cn.tf.bos.service;

import java.util.List;

import javax.annotation.Resource;

import cn.tf.bos.dao.BaseDao;
import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;

public abstract class BaseService {
	@Resource(name="userDao")
	protected BaseDao<User> userDao ;
		
	@Resource(name="standardDao")
	protected BaseDao<Standard>  standardDao;
	
	
	@Resource(name="staffdDao")
	protected BaseDao<Staff>  staffDao;
	
	// 分页通用代码
	public <T> PageResponseBean pageQuery(PageRequestBean pageRequestBean, BaseDao<T> dao) {
		PageResponseBean pageResponseBean = new PageResponseBean();

		// 满足当前条件，记录总条数
		long total = dao.findTotal(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);

		// 查询当前页显示数据
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows(); // 　从哪条开始
		int maxResults = pageRequestBean.getRows(); // 返回记录数
		pageRequestBean.getDetachedCriteria().setProjection(null); // 清除之前 rowCount的投影效果
		List<T> data = dao.findByPage(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(data);

		return pageResponseBean;
	}
}
