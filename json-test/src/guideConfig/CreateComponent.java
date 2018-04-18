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

public class CreateComponent {

	
	@Test
	public void test()  {
		String callCommand = "ember generate component %s";
		Root root = new Root("create_component", "创建组件(component)","component", callCommand, "IProject");
		
		PageControl pageControl1 = new PageControl("component_title_label","Label","组件名称(component):","SWT.NONE");
		PageControl pageControl1_1 = new PageControl("0","Text","请输入组件路径或名称(路径用'/')","SWT.BORDER");
		
		Page page1 = new Page("page1");
		page1.getPageContents().add(pageControl1);
		page1.getPageContents().add(pageControl1_1);
		
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
			                new FileOutputStream(new File("C:\\Users\\ThinkPad\\Desktop\\开发向导\\配置文件\\创建组件.config")), "UTF-8")); 
			fileWriter.write(value);
			fileWriter.flush();
			Root p = objectMapper.readValue(value, Root.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
