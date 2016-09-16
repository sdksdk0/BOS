package cn.tf.bos.service.bc;

import cn.tf.bos.domain.bc.Decidedzone;
import cn.tf.bos.page.PageQuery;

public interface  DecidedzoneService extends PageQuery{

	void saveOrUpdate(Decidedzone decidedzone, String[] subareaId);

}
