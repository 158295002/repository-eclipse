package leaveProcessTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class LeaveProcessTest {

	private String proc_def_id = "leaveProcess";

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void importResourceAndDeploy() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("./diagrams/leaveProcess2.bpmn");
		InputStream inputStream1 = this.getClass().getClassLoader()
				.getResourceAsStream("./diagrams/leaveProcess2.png");
		processEngine.getRepositoryService().createDeployment().addInputStream("leaveProcess2.bpmn", inputStream)
				.addInputStream("leaveProcess2.png", inputStream1).name("驳回流程").deploy();
		System.out.println("部署成功");
	}

	@Test
	public void listAllDeploy() {
		List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery()
				/* .processDefinitionKey(proc_def_id) */.list();
		for (Deployment deployment : deployments) {
			processEngine.getRepositoryService().deleteDeployment(deployment.getId(), true);
		}
		System.out.println("删除成功");
	}

	@Test
	public void startInstance() {
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println("启动成功");
	}

	/**
	 * 流程实例是继承执行对象,所以查询流程实例就是查询执行对象 查询的是execution的表 也可通过查询历史流程实例表 procinst
	 * 看是否endTime的值为空
	 */
	@Test
	public void taskIsComplete() {
		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id).singleResult();// 可能返回的不只一个，所以会有报错的可能，要返回单个，需根据流程实例的ID
		if (pi == null) {
			System.out.println("流程正常执行！！！，已经结束了");
		}else {
			System.out.println("还没结束");
		}

	}

	/**
	 */
	@Test
	public void completeTask() {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processDefinitionKey(proc_def_id).list();
		if (tasks == null) {
			return;
		}
		for (Task task : tasks) {
			// 临时修改代理人
//			processEngine.getTaskService().setAssignee(task.getId(), "修改人");
			// 组任务分配给个人任务（认领任务）：
			// processEngine.getTaskService().claim(taskId, userId);
			Map<String,Object> varMap = new HashMap<>();
			varMap.put("message", "不同意");
			processEngine.getTaskService().complete(task.getId(),varMap);
		}
		System.out.println("任务完成");
	}

}
