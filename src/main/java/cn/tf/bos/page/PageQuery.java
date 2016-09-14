package cn.tf.bos.page;


/**
 * 分页查询接口
 */
public interface PageQuery {
	/**
	 * 分页查询
	 * 
	 * @param pageRequestBean
	 * @return
	 */
	public PageResponseBean findByPage(PageRequestBean pageRequestBean);
}
