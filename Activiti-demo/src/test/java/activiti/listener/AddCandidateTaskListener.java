package activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class AddCandidateTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		//����������ӳ�Ա ��ӵ�identitiyLink����
		delegateTask.addCandidateUser("��������");
	}

}
