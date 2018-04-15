package subProcessTest;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class SubProcessTest {

	private String proc_def_id = "subProcessTest";

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy() {
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("子流程/subProcess.bpmn")
		.addClasspathResource("").name("子流程测试1").deploy();
		System.out.println(deployment.getId());
	}
	@Test
	public void deleteDeploment(){
		List<ProcessDefinition> processDefinitions =  processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(proc_def_id)
		.list();
		for (ProcessDefinition processDefinition : processDefinitions) {
			String id = processDefinition.getDeploymentId();
			processEngine.getRepositoryService().deleteDeployment(id,true);
		}
		System.out.println(processDefinitions);
	}
	
	
	@Test
	public void startInstance(){
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println(processInstance.getName());
	}
	
	@Test
	public void completeTask(){
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processDefinitionKey(proc_def_id).list();
		for (Task task : tasks) {
			processEngine.getTaskService().complete(task.getId());
		}
		System.out.println(tasks);
	}
	
	
	
	
	
	
	
	
	
	
	

}
