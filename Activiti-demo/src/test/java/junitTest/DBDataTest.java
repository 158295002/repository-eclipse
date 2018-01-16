package junitTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class DBDataTest {

	/**
	 * 代码初始化数据库
	 */
	@Test
	public void initProcessFromCodeTest() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?useUnicode=true&amp;characterEncoding=utf-8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("root");
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine.getName());
	}

	/**
	 * 从配置文件初始化数据库
	 */
	@Test
	public void initFromXmlTest() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine.getName());
	}
	
	/**
	 * 调用现有的方法进行创建
	 */
	@Test
	public void initFromSourceCodeTest() {
		ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
		System.out.println(processEngine.getName());
		
	}

}
