package junitTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class SequenceFlowTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("����")
				.addClasspathResource("./diagrams/SequenceFlow.bpmn")
				.addClasspathResource("./diagrams/SequenceFlow.png").deploy();
		System.out.println("����ɹ�");
	}

	@Test
	public void listAllDeployment() {
		List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery().list();
		deployments.stream().forEach(dem -> processEngine.getRepositoryService().deleteDeployment(dem.getId(), true));
		System.out.println("ɾ���ɹ�");
	}

	@Test
	public void startInstance() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionKey("SequenceFlow").latestVersion().singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		processEngine.getRuntimeService().startProcessInstanceByKey("SequenceFlow");
		System.out.println("�����ɹ�");
	}

	@Test
	public void completeTask() {
		List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processDefinitionKey("SequenceFlow").list();
		String var = "message";
		Map<String, Object> valiableMap = new HashMap<>();
		if (processInstances != null) {
			if (processInstances != null && processInstances.size() >= 1) {
				ProcessInstance processInstance = processInstances.get(0);
				List<Task> tasks = processEngine.getTaskService().createTaskQuery()
						.processInstanceId(processInstance.getProcessInstanceId()).list();
				for (Task task : tasks) {
					valiableMap.put(var, "���Ǳ���");
					processEngine.getTaskService().complete(task.getId(), valiableMap);
				}
			}
		}
		System.out.println("�������");

	}

}
