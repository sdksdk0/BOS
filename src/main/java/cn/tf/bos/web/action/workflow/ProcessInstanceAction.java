package cn.tf.bos.web.action.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.ProcessInstanceQuery;
import org.jbpm.api.model.ActivityCoordinates;

import com.opensymphony.xwork2.ActionContext;

import cn.tf.bos.web.action.BaseAction;

public class ProcessInstanceAction extends BaseAction{

	//查看正在运行的流程实例
	public String list(){
		//获得executionService
		ExecutionService  executionService=processEngine.getExecutionService();
		ProcessInstanceQuery  query=executionService.createProcessInstanceQuery();
		List<ProcessInstance> processInstances=query.list();
		ActionContext.getContext().put("processInstances", processInstances);
		return "list";
	}
	
	//显示实例流程图
	public String showpng(){
		//通过实例id查询发布id和图片name
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessInstance processInstance = executionService.createProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult();

		// 先查询流程定义
		ProcessDefinitionQuery processDefinitionQuery = processEngine.getRepositoryService().createProcessDefinitionQuery();
		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processInstance.getProcessDefinitionId()).uniqueResult();

		String deploymentId = processDefinition.getDeploymentId();
		String imageResourceName = processDefinition.getImageResourceName();

		// 通过值栈传递给页面
		ActionContext.getContext().put("deploymentId", deploymentId);
		ActionContext.getContext().put("imageResourceName", imageResourceName);
		List<ActivityCoordinates>  list=new ArrayList<ActivityCoordinates>();
		Set<String> activityNames=processInstance.findActiveActivityNames();
		for (String name : activityNames) {
			ActivityCoordinates activityCoordinates = processEngine.getRepositoryService().getActivityCoordinates(processDefinition.getId(), name);
			list.add(activityCoordinates);
		}
		ActionContext.getContext().put("list", list);
		
		
		return "showpng";
	}
	
	private String processInstanceId;
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
