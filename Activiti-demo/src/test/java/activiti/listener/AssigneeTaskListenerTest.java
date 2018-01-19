package activiti.listener;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * act_ru_identitylink表存放任务的办理人，包括个人任务和组任务，表示正在执行的任务
act_hi_identitylink表存放任务的办理人，包括个人任务和组任务，表示历史任务
区别在于：如果是个人任务TYPE的类型表示participant（参与者）
		 如果是组任务TYPE的类型表示candidate（候选者）和participant（参与者）

 * @author dingqin
 * 2018年1月19日
 *
 */
public class AssigneeTaskListenerTest {

	private String proc_def_id = "AssigneeTaskListener";

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void importResourceAndDeploy() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("./bpmnResources/assigneeListener2.bpmn");
		InputStream inputStream1 = this.getClass().getClassLoader()
				.getResourceAsStream("./bpmnResources/assigneeListener2.png");
		processEngine.getRepositoryService().createDeployment().addInputStream("assigneeListener2.bpmn", inputStream)
				.addInputStream("assigneeListener2.png", inputStream1).name("代理监听流程").deploy();
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
		}

	}

	/**
	 * 接收任务的task记录部署在task表中，它只在execution表中,所以用taskService是查找不到数据
	 */
	@Test
	public void completeTask() {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processDefinitionKey(proc_def_id).list();
		if (tasks == null) {
			return;
		}
		for (Task task : tasks) {
			// 临时修改代理人
			processEngine.getTaskService().setAssignee(task.getId(), "修改人");
			// 组任务分配给个人任务（认领任务）：
			// processEngine.getTaskService().claim(taskId, userId);

			processEngine.getTaskService().complete(task.getId());
		}
		System.out.println("任务完成");
	}

}
