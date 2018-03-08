package junitTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;

public class LeaveProcessTest {
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 流程发布
	 */
	@Test
	public void deployProcess() {
//		InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/myProcess.zip");
//		ZipInputStream zipInputStream = new ZipInputStream(in);
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService
		.createDeployment()
		.name("first3 deployment")
		//��ʽ1
		.addClasspathResource("diagrams/leaveProcess.bpmn")
		.addClasspathResource("diagrams/leaveProcess.png")
		//��ʽ2
//		.addZipInputStream(zipInputStream)
		.deploy();
		String sdate = formatDate(deployment);
		System.out.println(deployment.getName()+";"+sdate);
//		processEngine.getRuntimeService().startProcessInstanceByKey("");
	}

	protected String formatDate(Deployment deployment) {
		Date date = deployment.getDeploymentTime();
		DateFormat format = new SimpleDateFormat("yyyy年mm月dd日:hh:mm:ss");
		String sdate = format.format(date);
		return sdate;
	}

	
	@Test
	public void delDeploymentTest(){
		List<ProcessDefinition> defines =  processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.latestVersion()
				.list();
		
		String deploymentId = defines.get(0).getDeploymentId();
		processEngine.getRepositoryService().deleteDeployment(deploymentId, true);
	}
	
	/**
	 * 流程启动
	 */
	@Test
	public void processInstanceTest() {
		List<ProcessDefinition> defines =  processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.latestVersion()
				.list();
		
		String key = (String) defines.stream().map(ProcessDefinition::getKey).findAny().get();
		ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey(key);
		System.out.println(instance.getId());
		System.out.println(instance.getProcessDefinitionId());
	}
	
	@Test
	public void TaskTest() {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().list();
		tasks.stream().forEach(task -> System.out.println(task.getAssignee()));
		
		
//		processEngine.getTaskService().complete("17502");
		
	}
	
	
	@Test
	public void saveImage(){
		 /**������ͼƬ�ŵ��ļ�����*/
        String deploymentId = "1";
        //��ȡͼƬ��Դ����
        List<String> list = processEngine.getRepositoryService()//
                        .getDeploymentResourceNames(deploymentId);
        
        //����ͼƬ��Դ������
        String resourceName = "";
        if(list!=null && list.size()>0){
            for(String name:list){
                if(name.indexOf(".png")>=0){
                    resourceName = name;
                }
            }
        }
        
        
        //��ȡͼƬ��������
        InputStream in = processEngine.getRepositoryService()//
                        .getResourceAsStream(deploymentId, resourceName);
        //��ͼƬ���ɵ�C�̵�Ŀ¼��
        File file = new File("C:\\Users\\Administrator\\Desktop\\test\\"+resourceName);
        try {
			FileCopyUtils.copy(in, new FileOutputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("�����ɹ�");
	}
	

}
