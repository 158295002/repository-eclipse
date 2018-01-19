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
 * act_ru_identitylink��������İ����ˣ�������������������񣬱�ʾ����ִ�е�����
act_hi_identitylink��������İ����ˣ�������������������񣬱�ʾ��ʷ����
�������ڣ�����Ǹ�������TYPE�����ͱ�ʾparticipant�������ߣ�
		 �����������TYPE�����ͱ�ʾcandidate����ѡ�ߣ���participant�������ߣ�

 * @author dingqin
 * 2018��1��19��
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
				.addInputStream("assigneeListener2.png", inputStream1).name("�����������").deploy();
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
			// ��ʱ�޸Ĵ�����
			processEngine.getTaskService().setAssignee(task.getId(), "�޸���");
			// �������������������������񣩣�
			// processEngine.getTaskService().claim(taskId, userId);

			processEngine.getTaskService().complete(task.getId());
		}
		System.out.println("�������");
	}

}
