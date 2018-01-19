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
 * ��������
 * 
 * @author Administrator
 *
 */
public class ParallelGateWayTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	private String proc_def_id = "parallelGateWay";

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("��������")
				.addClasspathResource("./diagrams/parallelGateWay.bpmn")
				.addClasspathResource("./diagrams/parallelGateWay.png").deploy();
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
		// ProcessDefinition processDefinition =
		// processEngine.getRepositoryService().createProcessDefinitionQuery()
		// .processDefinitionKey(proc_def_id).latestVersion().singleResult();
		// String deploymentId = processDefinition.getDeploymentId();
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id);
		System.out.println("�����ɹ�");
	}

	/**
	 * �������صĹ����ǻ��ڽ���������˳�����ģ� ��֧(fork)�� ���к���������˳������Ϊÿ��˳����������һ��������֧�� ���(join)��
	 * ���е��ﲢ�����أ��ڴ˵ȴ��Ľ����֧�� ֱ�����н���˳�����ķ�֧�������Ժ� ���̾ͻ�ͨ��������� ��parallelGateWay2
	 * 
	 * parallelGateWay���Լ�ִ���Լ��ģ�����ǰ����
	 * 
	 * 5�� �������ز������������ ��ʹ˳�����ж�����������Ҳ�ᱻ���ԡ�
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
		System.out.println("�������");

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
