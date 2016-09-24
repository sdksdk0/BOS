package cn.tf.bos.service.impl.workflow;

import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;

import cn.tf.bos.domain.zm.InStore;
import cn.tf.bos.domain.zm.OutStore;
import cn.tf.bos.domain.zm.ReceiveGoodsInfo;
import cn.tf.bos.domain.zm.TransferInfo;
import cn.tf.bos.domain.zm.ZhongZhuanInfo;
import cn.tf.bos.service.BaseService;
import cn.tf.bos.service.workflow.BosTaskSerice;

public class BosTaskServiceImpl extends BaseService implements BosTaskSerice{

	@Override
	public void complieteTransferInfoTask(TransferInfo transferInfo,String taskId) {
		
		//业务数据持久化
		transferInfoDao.save(transferInfo);
		
		TaskService taskService=processEngine.getTaskService();
		ZhongZhuanInfo  zhongzhuanInfo=(ZhongZhuanInfo) taskService.getVariable(taskId, "zhongzhuanInfo");
		zhongzhuanInfo.setArrive(transferInfo.getArrive());
		zhongzhuanInfo.getTransferInfos().add(transferInfo);
		
		//办理任务，自动流转
		if(zhongzhuanInfo.getArrive().equals("0")){
			Task task = taskService.getTask(taskId);
			completeTaskByCreateTransiton(task, "中转环节", "to 中转环节");
		}else{
			taskService.completeTask(taskId, "to 入库"); // 流向入库
		}
	};

	
	/**
	 * 自由流转
	 * 
	 * @param task
	 *            当前任务
	 * @param destActivityName
	 *            目标节点name 属性
	 * @param createTransitionName
	 *            流转transition的name属性
	 */
	public void completeTaskByCreateTransiton(Task task, String destActivityName, String createTransitionName) {
		// 这里不会影响事务
		EnvironmentImpl envImpl = ((EnvironmentFactory) processEngine).openEnvironment(); // 开启环境
		try {

			// 动态回退
			// 获得流程实例对象
			ExecutionImpl e = (ExecutionImpl) processEngine.getExecutionService().findExecutionById(task.getExecutionId());
			// 获得 活动对象，是当前任务活动 对象 ---- 中转环节
			ActivityImpl currentActivityImpl = e.getActivity();

			// 获得流程定义 jpdl.xml 文件
			ProcessDefinitionImpl processDefinitionImpl = currentActivityImpl.getProcessDefinition();

			// 获得目标活动节点 ---- 中转环节
			ActivityImpl destActivityImpl = processDefinitionImpl.findActivity(destActivityName);
			// 动态创建 transition 流转
			TransitionImpl toApply = currentActivityImpl.createOutgoingTransition();
			toApply.setSource(currentActivityImpl); // 流转出发点
			toApply.setDestination(destActivityImpl); // 流转目标点
			toApply.setName(createTransitionName); // 为流转命名
			processEngine.getTaskService().completeTask(task.getId(), createTransitionName); // 完成任务，使用动态创建流转
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			envImpl.close();
		}
	}


	@Override
	public void complieteInStoreTask(InStore inStore, String taskId) {
		//业务数据持久化
		inStoreDao.save(inStore);
		TaskService taskService=processEngine.getTaskService();
		ZhongZhuanInfo  zhongzhuanInfo=(ZhongZhuanInfo) taskService.getVariable(taskId, "zhongzhuanInfo");
		zhongzhuanInfo.setInStore(inStore);
		
		taskService.completeTask(taskId,"to 出库");
	}


	@Override
	public void complieteOutStore(OutStore outStore, String taskId) {
		outStoreDao.save(outStore);
		TaskService taskService=processEngine.getTaskService();
		ZhongZhuanInfo  zhongzhuanInfo=(ZhongZhuanInfo) taskService.getVariable(taskId, "zhongzhuanInfo");
		zhongzhuanInfo.setOutStore(outStore);
		
		taskService.completeTask(taskId,"to 配送签收");
	

	}


	@Override
	public void complieteReceGoodsInfoTask(ReceiveGoodsInfo receiveGoodsInfo,
			String taskId) {
		receiveGoodsInfoDao.save(receiveGoodsInfo);
		TaskService taskService=processEngine.getTaskService();
		ZhongZhuanInfo  zhongzhuanInfo=(ZhongZhuanInfo) taskService.getVariable(taskId, "zhongzhuanInfo");
		zhongzhuanInfo.setReceiveGoodsInfo(receiveGoodsInfo);
		
		taskService.completeTask(taskId,"to end1");
	
	}

}
