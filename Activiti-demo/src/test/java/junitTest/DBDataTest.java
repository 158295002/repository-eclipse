package junitTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class DBDataTest {

	/**
	 * �����ʼ�����ݿ�
	 */
	@Test
	public void initProcessFromCodeTest() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo?useUnicode=true&amp;characterEncoding=utf-8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("root");
		processEngineConfiguration.setActivityFontName("����");
		processEngineConfiguration.setLabelFontName("����");
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine.getName());
	}

	/**
	 * �������ļ���ʼ�����ݿ�
	 */
	@Test
	public void initFromXmlTest() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println(processEngine.getName());
	}
	
	/**
	 * �������еķ������д���
	 */
	@Test
	public void initFromSourceCodeTest() {
		ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
		System.out.println(processEngine.getName());
		
	}

}
