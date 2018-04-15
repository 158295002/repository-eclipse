package leaveProcessTest;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;

public class LeaveServiceTaskDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.getEngineServices();
		String message = (String) execution.getVariable("message");
		execution.setVariable("message", "ЭЌвт");
		System.out.println(message);
		execution.getProcessInstanceId();
		Task task = execution.getEngineServices().getTaskService().createTaskQuery()
				.processInstanceId(execution.getProcessInstanceId()).singleResult();
	       ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)execution.getEngineServices().getRepositoryService()).getDeployedProcessDefinition(execution.getProcessDefinitionId());  
	
	       List<ActivityImpl> ac = def.getActivities();
	}

}
