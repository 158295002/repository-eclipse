package activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class AddCandidateTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		//向组任务添加成员 添加到identitiyLink表中
		delegateTask.addCandidateUser("新增的人");
	}

}
