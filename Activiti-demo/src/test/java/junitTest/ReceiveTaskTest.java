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
 * ������Ϣ����
 * @author dingqin
 * 2018��1��19��
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
				.addInputStream("receiveTask.png", inputStream1).name("������������").deploy();
		System.out.println("����ɹ�");
	}

	@Test
	public void listAllDeploy() {
		List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery()
				/* .processDefinitionKey(proc_def_id) */.list();
		for (Deployment deployment : deployments) {
			processEngine.getRepositoryService().deleteDeployment(deployment.getId(), true);
		}
		System.out.println("ɾ���ɹ�");
	}

	@Test
	public void startInstance() {
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println("�����ɹ�");
	}


	/**
	 * ����ʵ���Ǽ̳�ִ�ж���,���Բ�ѯ����ʵ�����ǲ�ѯִ�ж���  ��ѯ����execution�ı�
	 * Ҳ��ͨ����ѯ��ʷ����ʵ����  procinst  ���Ƿ�endTime��ֵΪ��
	 */
	@Test
	public void taskIsComplete(){
		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id)
				.singleResult();//���ܷ��صĲ�ֻһ�������Ի��б���Ŀ��ܣ�Ҫ���ص��������������ʵ����ID
		if (pi == null) {
			System.out.println("��������ִ�У��������Ѿ�������");
		}

	}
	
	/**
	 * ���������task��¼������task���У���ֻ��execution����,������taskService�ǲ��Ҳ�������
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
		System.out.println("�������");
	}
	
	@Test
	public void completeExecution() {
		String scoreId = "ͳ�Ƴɼ�ID";
		String notifyId = "��֪ѧ��ID";
		Execution execution = processEngine.getRuntimeService().createExecutionQuery().processDefinitionKey(proc_def_id)
				.activityId(scoreId)//
				.singleResult();
		Map<String, Object> varMap = new HashMap<>();
		if (execution != null) {
			varMap.put(scoreId, "ͳ�Ƴɼ����������");
//			�������ִ��һ������������execution,ʹ��signal�����������źţ���������ǰ�����Ѿ�����ˣ���������ִ��
			processEngine.getRuntimeService().signal(execution.getId(), varMap);
		}

		execution = processEngine.getRuntimeService().createExecutionQuery().processDefinitionKey(proc_def_id)
				.activityId(notifyId)//
				.singleResult();
		if (execution != null) {
			varMap.put(notifyId, "����ÿ�˳ɼ�");
			processEngine.getRuntimeService().signal(execution.getId(), varMap);
		}
		System.out.println("����ִ�гɹ�");

		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id)
				.singleResult();
		if (pi == null) {
			System.out.println("��������ִ�У��������Ѿ�������");
		}

	}

}
