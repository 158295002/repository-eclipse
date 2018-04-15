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
 * ��������
 * @author Administrator
 *
 */
public class ExclusiveGateWayTest {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	private String proc_def_id = "exclusiveGateWay";

	@Test
	public void importResourceAndDepoly() {
		processEngine.getRepositoryService().createDeployment().name("��������")
				.addClasspathResource("./diagrams/exclusiveGateWay.bpmn")
				.addClasspathResource("./diagrams/exclusiveGateWay.png").deploy();
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
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
//				.processDefinitionKey(proc_def_id).latestVersion().singleResult();
//		String deploymentId = processDefinition.getDeploymentId();
		Map<String,Object> map = new HashMap<>();
		map.put("name", "ding");
		processEngine.getRuntimeService().startProcessInstanceByKey(proc_def_id,map);
		System.out.println("�����ɹ�");
	}

	/**
	 * ��������ֻ�᷵��һ�������������ִ�е���������ʱ������������Զ��������س��ڣ����ϵ��¼���������ֵ�һ�����߽��Ϊtrue����û������������(Ĭ��Ϊ����)����������
	 */
	@Test
	public void completeTask() {
//		List<ProcessInstance> processInstances = processEngine.getRuntimeService().createProcessInstanceQuery()
//				.processDefinitionKey(proc_def_id).list();
//		String var = "day";
//		Map<String, Object> valiableMap = new HashMap<>();
//		if (processInstances != null) {
//			if (processInstances != null && processInstances.size() >= 1) {
//				ProcessInstance processInstance = processInstances.get(0);
//				List<Task> tasks = processEngine.getTaskService().createTaskQuery()
//						.processInstanceId(processInstance.getProcessInstanceId()).list();
//				for (Task task : tasks) {
//					valiableMap.put(var, 2);
//					
//					processEngine.getTaskService().complete(task.getId(), valiableMap);
//				}
//			}
//		}
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processDefinitionKey(proc_def_id).list();
		LeaveModel leaveModel = new LeaveModel();
		leaveModel.setName("ding");
		leaveModel.setLeaveDays(2);
		leaveModel.setNextAssignee("������");
		Map<String,Object> varMap = new HashMap<>();
		varMap.put("leaveModel", leaveModel);
		for (Task task : tasks) {
			processEngine.getTaskService().complete(task.getId(), varMap);
		}
		
		System.out.println("�������");

	}

}
