package activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class AssigneeTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee("������");
	}
}
