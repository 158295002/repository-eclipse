package activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MasterTaskListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName = null;
	
//	
//	public MasterTaskListener(String fieldName) {
//		super();
//		this.fieldName = fieldName;
//	}
	
	

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println(fieldName);

	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
