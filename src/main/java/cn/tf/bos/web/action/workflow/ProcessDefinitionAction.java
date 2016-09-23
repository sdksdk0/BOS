package cn.tf.bos.web.action.workflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.RepositoryService;
import org.jbpm.jpdl.internal.activity.ForEachActivity;

import com.opensymphony.xwork2.ActionContext;

import cn.tf.bos.web.action.BaseAction;

//流程定义管理
public class ProcessDefinitionAction extends  BaseAction{
	
	//发布流程定义
	public  String deploy() throws FileNotFoundException{
		//上传的文件是一个zip压缩包
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		NewDeployment deployment = repositoryService.createDeployment();
		deployment.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream(deploy)));
		deployment.deploy();
		
		return "deploy";
	}
	private File  deploy;
	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}
	
	
	//查询所有的业务流程定义
	public String list(){
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		List<ProcessDefinition>   list=repositoryService.createProcessDefinitionQuery().orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
		//只显示最高版本
		Map<String,ProcessDefinition>  map=new HashMap<String,ProcessDefinition>();
		for (ProcessDefinition processDefinition : list) {
			map.put(processDefinition.getKey(), processDefinition);
		}
		ActionContext.getContext().put("processDefinition", map.values());
		
		return "list";
	}
	
	
	//查看流程图
	public String viewpng(){
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		in=repositoryService.getResourceAsStream(deploymentId, imageResourceName);
		
		return "viewpng";
	}
	public 	InputStream in;
	public InputStream getInputStream(){
		return in;
	}
	private String deploymentId;
	private String imageResourceName;
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public void setImageResourceName(String imageResourceName) {
		this.imageResourceName = imageResourceName;
	}

	

}
