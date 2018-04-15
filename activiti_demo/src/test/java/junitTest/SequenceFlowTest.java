package junitTest;

import java.util.ArrayList;
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

/**
 * 连线设置条件 和节点设置assignee变量测试
 * 
 * @author dingqin 2018年1月19日
 *
 */
public class SequenceFlowTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("连线")
				.addClasspathResource("./diagrams/SequenceFlow.bpmn")
				.addClasspathResource("./diagrams/SequenceFlow.png").deploy();
		System.out.println("部署成功");
	}

	@Test
	public void listAllDeployment() {
		List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery().list();
		deployments.stream().forEach(dem -> processEngine.getRepositoryService().deleteDeployment(dem.getId(), true));
		System.out.println("删除成功");
	}

	@Test
	public void startInstance() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionKey("SequenceFlow").latestVersion().singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		Map<String, Object> varMap = new HashMap<>();
		List<String> names = new ArrayList<>();
		names.add("张三");
		names.add("李四");
		varMap.put("userName", names);
		processEngine.getRuntimeService().startProcessInstanceByKey("SequenceFlow", varMap);
		System.out.println("启动成功");
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
					valiableMap.put(var, "必须");
					valiableMap.put("masterName", "王总");
					List varMap = (List) processEngine.getTaskService().getVariable(task.getId(), "userName");
					String v = (String) processEngine.getTaskService().getVariable(task.getId(), "masterName");
					processEngine.getTaskService().complete(task.getId(), valiableMap);
				}
			}
		}
		System.out.println("任务完成");

	}

}
