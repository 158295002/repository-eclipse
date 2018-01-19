package activiti.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;


public class StartEventExecutionListener implements ExecutionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		Map<String, Object> varMap = new HashMap<>();
		List<String> names = new ArrayList<>();
		names.add("…Í«Î»ÀA");
		names.add("…Í«Î»ÀB");
		varMap.put("assignee", names);
		execution.setVariables(varMap);
	}

}
