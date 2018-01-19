package junitTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 接受消息任务
 * @author dingqin
 * 2018年1月19日
 *
 */
public class ReceiveTaskTest {

	private String proc_def_id = "receiveTask";

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
	public void importResourceAndDeploy() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("./diagrams/receiveTask.bpmn");
		InputStream inputStream1 = this.getClass().getClassLoader().getResourceAsStream("./diagrams/receiveTask.png");
		processEngine.getRepositoryService().createDeployment().addInputStream("receiveTask.bpmn", inputStream)
				.addInputStream("receiveTask.png", inputStream1).name("接收任务流程").deploy();
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
	 * 流程实例是继承执行对象,所以查询流程实例就是查询执行对象  查询的是execution的表
	 * 也可通过查询历史流程实例表  procinst  看是否endTime的值为空
	 */
	@Test
	public void taskIsComplete(){
		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id)
				.singleResult();//可能返回的不只一个，所以会有报错的可能，要返回单个，需根据流程实例的ID
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
			processEngine.getTaskService().complete(task.getId());
		}
		System.out.println("任务完成");
	}
	
	@Test
	public void completeExecution() {
		String scoreId = "统计成绩ID";
		String notifyId = "告知学生ID";
		Execution execution = processEngine.getRuntimeService().createExecutionQuery().processDefinitionKey(proc_def_id)
				.activityId(scoreId)//
				.singleResult();
		Map<String, Object> varMap = new HashMap<>();
		if (execution != null) {
			varMap.put(scoreId, "统计成绩结果出来啦");
//			流程向后执行一步：往后推移execution,使用signal给流程引擎信号，告诉他当前任务已经完成了，可以往后执行
			processEngine.getRuntimeService().signal(execution.getId(), varMap);
		}

		execution = processEngine.getRuntimeService().createExecutionQuery().processDefinitionKey(proc_def_id)
				.activityId(notifyId)//
				.singleResult();
		if (execution != null) {
			varMap.put(notifyId, "告诉每人成绩");
			processEngine.getRuntimeService().signal(execution.getId(), varMap);
		}
		System.out.println("任务执行成功");

		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id)
				.singleResult();
		if (pi == null) {
			System.out.println("流程正常执行！！！，已经结束了");
		}

	}

}
