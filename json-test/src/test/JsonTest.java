package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import guideConfigModel.Page;
import guideConfigModel.PageControl;
import guideConfigModel.Root;

public class JsonTest {

	
	@Test
	public void test()  {
		PageControl nextChild = new PageControl("route_title_label","Label","路由名称","SWT.NONE");
		PageControl nextChild2 = new PageControl("route_title_text","Text","请输入路由名称:","SWT.NONE");
		PageControl nextChild3 = new PageControl(" preview","Button","是否预览创建结果","SWT.CHECK");
		List<PageControl> childs = new ArrayList<>();
		childs.add(nextChild);
		childs.add(nextChild2);	
		childs.add(nextChild3);
		
		Page child = new Page("page1");
		child.getPageContents().add(nextChild);
		child.getPageContents().add(nextChild2);
		child.getPageContents().add(nextChild3);
		
		Page child2 = new Page("page2");
		child2.getPageContents().add(nextChild);
		child2.getPageContents().add(nextChild2);
		child2.getPageContents().add(nextChild3);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String callCommand = "ember generate route %s --dry-run";
//		Person p1 = new Person("create_component", "创建组建","component","component.template");
		Root p1 = new Root("create_route", "创建路由","route", callCommand, "IProject");
		p1.getPages().add(child);
		p1.getPages().add(child2);
		
		try {
			String value = objectMapper.writeValueAsString(p1);
			@SuppressWarnings("resource")
//			FileWriter fileWriter = new FileWriter(new File("C:\\Users\\ThinkPad\\Desktop\\json.txt"));
			Writer fileWriter = new BufferedWriter(  
			        new OutputStreamWriter(  
			                new FileOutputStream(new File("C:\\Users\\ThinkPad\\Desktop\\json.txt")), "UTF-8")); 
			fileWriter.write(value);
			fileWriter.flush();
			Root p = objectMapper.readValue(value, Root.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
