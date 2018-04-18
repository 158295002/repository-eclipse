package guideConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import guideConfigModel.Page;
import guideConfigModel.PageControl;
import guideConfigModel.Root;

public class CreateModuleComponent {

	
	@Test
	public void test()  {
		String callCommand = "ember generate component %s -ir %s";
		Root root = new Root("create_module_component", "创建模块组件","module_component", callCommand, "IProject");
		
		PageControl pageControl1 = new PageControl("module_component_title_label","Label","组件名称:","SWT.NONE");
		PageControl pageControl1_1 = new PageControl("0","Text","请输入组件名称","SWT.BORDER");
		PageControl pageControl2 = new PageControl("module_component_title_label","Label","模块名:","SWT.NONE");
		PageControl pageControl2_1 = new PageControl("1","Text","请输入存在的模块名称","SWT.BORDER");
		
		Page page1 = new Page("page1");
		page1.getPageContents().add(pageControl1);
		page1.getPageContents().add(pageControl1_1);
		page1.getPageContents().add(pageControl2);
		page1.getPageContents().add(pageControl2_1);
		
		Page page2 = new Page("page2");
		PageControl container = new PageControl("preview","Group","创建的结果预览","SWT.NONE");
		container.setContainer(true);
		PageControl child = new PageControl("show", "Label", "", "SWT.WRAP | SWT.READ_ONLY");
		container.getChildControls().add(child);
		
		page2.getPageContents().add(container);
		
		ObjectMapper objectMapper = new ObjectMapper();
		root.getPages().add(page1);
		root.getPages().add(page2);
		
		try {
			String value = objectMapper.writeValueAsString(root);
			@SuppressWarnings("resource")
//			FileWriter fileWriter = new FileWriter(new File("C:\\Users\\ThinkPad\\Desktop\\json.txt"));
			Writer fileWriter = new BufferedWriter(  
			        new OutputStreamWriter(  
			                new FileOutputStream(new File("C:\\Users\\ThinkPad\\Desktop\\开发向导\\配置文件\\创建模块组件.config")), "UTF-8")); 
			fileWriter.write(value);
			fileWriter.flush();
			Root p = objectMapper.readValue(value, Root.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
