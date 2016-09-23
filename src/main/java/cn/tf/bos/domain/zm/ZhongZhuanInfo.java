package cn.tf.bos.domain.zm;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.task.Task;

import cn.tf.bos.domain.qp.WorkOrderManage;




/**
 * 中转流程 完整信息
 *
 */
public class ZhongZhuanInfo {
	private Long id;
	private Task currentTask; // 当前任务（不存储）
	
	// 这里为什么用List ，因为要保证中转信息顺利
	private List<TransferInfo> transferInfos = new ArrayList<TransferInfo>(); // 多次中转信息
	
	private String arrive; //是否到达
	
	private InStore inStore; // 入库信息
	private OutStore outStore; // 出库信息
	private ReceiveGoodsInfo receiveGoodsInfo; // 配送信息
	
	private WorkOrderManage workOrderManage; //工作单信息
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Task getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	public List<TransferInfo> getTransferInfos() {
		return transferInfos;
	}
	public void setTransferInfos(List<TransferInfo> transferInfos) {
		this.transferInfos = transferInfos;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public InStore getInStore() {
		return inStore;
	}
	public void setInStore(InStore inStore) {
		this.inStore = inStore;
	}
	public OutStore getOutStore() {
		return outStore;
	}
	public void setOutStore(OutStore outStore) {
		this.outStore = outStore;
	}
	public ReceiveGoodsInfo getReceiveGoodsInfo() {
		return receiveGoodsInfo;
	}
	public void setReceiveGoodsInfo(ReceiveGoodsInfo receiveGoodsInfo) {
		this.receiveGoodsInfo = receiveGoodsInfo;
	}
	public WorkOrderManage getWorkOrderManage() {
		return workOrderManage;
	}
	public void setWorkOrderManage(WorkOrderManage workOrderManage) {
		this.workOrderManage = workOrderManage;
	}
	@Override
	public String toString() {
		return "中转流程信息 [中转环节=" + transferInfos + ", 是否到达="
				+ arrive + ", 入库信息=" + inStore + ", 出库信息=" + outStore
				+ ", 签收信息=" + receiveGoodsInfo
				+ ", 工作单信息=" + workOrderManage + "]";
	}
	
	
}
