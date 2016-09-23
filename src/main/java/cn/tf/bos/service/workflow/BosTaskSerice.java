package cn.tf.bos.service.workflow;

import cn.tf.bos.domain.zm.TransferInfo;

public interface BosTaskSerice {

	void complieteTransferInfoTask(TransferInfo transferInfo, String taskId);

}
