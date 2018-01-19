package junitTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 排他网关
 * @author Administrator
 *
 */
public class ExclusiveGateWayTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	private String proc_def_id = "exclusiveGateWay";

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("排他网关")
				.addClasspathResource("./diagrams/exclusiveGateWay.bpmn")
				.addClasspathResource("./diagrams/exclusiveGateWay.png").deploy();
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
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
//				.processDefinitionKey(proc_def_id).latestVersion().singleResult();
//		String deploymentId = processDefinition.getDeploymentId();
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println("启动成功");
	}

	/**
	 * 决策网关只会返回一条结果。当流程执行到排他网关时，流程引擎会自动检索网关出口，从上到下检索如果发现第一条决策结果为true或者没有设置条件的(默认为成立)，则流出。
	 */
	@Test
	public void completeTask() {
		List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processDefinitionKey(proc_def_id).list();
		String var = "day";
		Map<String, Object> valiableMap = new HashMap<>();
		if (processInstances != null) {
			if (processInstances != null && processInstances.size() >= 1) {
				ProcessInstance processInstance = processInstances.get(0);
				List<Task> tasks = processEngine.getTaskService().createTaskQuery()
						.processInstanceId(processInstance.getProcessInstanceId()).list();
				for (Task task : tasks) {
					valiableMap.put(var, 2);
					processEngine.getTaskService().complete(task.getId(), valiableMap);
				}
			}
		}
		System.out.println("任务完成");

	}

}
