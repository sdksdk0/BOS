package cn.tf.bos.jbpm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class JbpmTest {
	
	@Autowired
	private ProcessEngine  processEngine;
	
	@Test
	public void test1() throws FileNotFoundException{
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		NewDeployment  deployment=repositoryService.createDeployment();
		deployment.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream("holiday.zip")));
		deployment.deploy();
		
	}

}
