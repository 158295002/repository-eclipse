package junitTest;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 并行网关
 * 
 * @author Administrator
 *
 */
public class ParallelGateWayTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	private String proc_def_id = "parallelGateWay";

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("并行网关")
				.addClasspathResource("./diagrams/parallelGateWay.bpmn")
				.addClasspathResource("./diagrams/parallelGateWay.png").deploy();
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
		// ProcessDefinition processDefinition =
		// processEngine.getRepositoryService().createProcessDefinitionQuery()
		// .processDefinitionKey(proc_def_id).latestVersion().singleResult();
		// String deploymentId = processDefinition.getDeploymentId();
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println("启动成功");
	}

	/**
	 * 并行网关的功能是基于进入和外出的顺序流的： 分支(fork)： 并行后的所有外出顺序流，为每个顺序流都创建一个并发分支。 汇聚(join)：
	 * 所有到达并行网关，在此等待的进入分支， 直到所有进入顺序流的分支都到达以后， 流程就会通过汇聚网关 如parallelGateWay2
	 * 
	 * parallelGateWay就自己执行自己的，可提前结束
	 * 
	 * 5） 并行网关不会解析条件。 即使顺序流中定义了条件，也会被忽略。
	 * 
	 */
	@Test
	public void completeTask() {
		List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processDefinitionKey(proc_def_id).list();
		if (processInstances != null) {
			if (processInstances != null && processInstances.size() >= 1) {
				ProcessInstance processInstance = processInstances.get(0);
				List<Task> tasks = processEngine.getTaskService().createTaskQuery()
						.processInstanceId(processInstance.getProcessInstanceId()).list();
				for (Task task : tasks) {
					processEngine.getTaskService().complete(task.getId());
					break;
				}
			}
		}
		System.out.println("任务完成");

	}

	@Test
	public void listHis() {
		List<HistoricProcessInstance> historicProcessInstances = processEngine.getHistoryService()
				.createHistoricProcessInstanceQuery().processDefinitionKey(proc_def_id).list();
		for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
			System.out.println(historicProcessInstance.getEndTime());
		}
	}

}
