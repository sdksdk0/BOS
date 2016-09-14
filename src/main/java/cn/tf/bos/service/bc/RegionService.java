package cn.tf.bos.service.bc;

import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.page.PageQuery;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;

public interface RegionService extends PageQuery{

	public void saveRegion(Region region);


}
