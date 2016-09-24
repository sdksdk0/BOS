package cn.tf.bos.service.workflow;

import cn.tf.bos.domain.zm.InStore;
import cn.tf.bos.domain.zm.OutStore;
import cn.tf.bos.domain.zm.ReceiveGoodsInfo;
import cn.tf.bos.domain.zm.TransferInfo;

public interface BosTaskSerice {

	void complieteTransferInfoTask(TransferInfo transferInfo, String taskId);

	void complieteInStoreTask(InStore inStore, String taskId);

	void complieteOutStore(OutStore outStore, String taskId);

	void complieteReceGoodsInfoTask(ReceiveGoodsInfo receiveGoodsInfo,
			String taskId);

}
