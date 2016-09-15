package cn.tf.bos.service.bc;

import java.util.List;

import cn.tf.bos.domain.bc.Region;
import cn.tf.bos.page.PageQuery;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;

public interface RegionService extends PageQuery{

	public void saveRegion(Region region);

	public List<Region> findAllRegions();

	public void saveOrUpdate(Region region);

	public void delete(String[] ids);


}
