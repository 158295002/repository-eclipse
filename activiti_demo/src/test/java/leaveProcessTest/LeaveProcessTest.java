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
				.addInputStream("leaveProcess2.png", inputStream1).name("��������").deploy();
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
	 * ����ʵ���Ǽ̳�ִ�ж���,���Բ�ѯ����ʵ�����ǲ�ѯִ�ж��� ��ѯ����execution�ı� Ҳ��ͨ����ѯ��ʷ����ʵ���� procinst
	 * ���Ƿ�endTime��ֵΪ��
	 */
	@Test
	public void taskIsComplete() {
		ProcessInstance pi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()//
				.processDefinitionKey(proc_def_id).singleResult();// ���ܷ��صĲ�ֻһ�������Ի��б���Ŀ��ܣ�Ҫ���ص��������������ʵ����ID
		if (pi == null) {
			System.out.println("��������ִ�У��������Ѿ�������");
		}else {
			System.out.println("��û����");
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
			// ��ʱ�޸Ĵ�����
//			processEngine.getTaskService().setAssignee(task.getId(), "�޸���");
			// �������������������������񣩣�
			// processEngine.getTaskService().claim(taskId, userId);
			Map<String,Object> varMap = new HashMap<>();
			varMap.put("message", "��ͬ��");
			processEngine.getTaskService().complete(task.getId(),varMap);
		}
		System.out.println("�������");
	}

}
