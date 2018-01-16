package cn.com.zhongli.test.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import cn.com.zhongli.test.extension.bean.AbstractClassInformation;
import cn.com.zhongli.test.extension.bean.Class;
import cn.com.zhongli.test.extension.bean.Student;

/**扩展点解析工厂
 * @author 钟利
 * @data 2012-12-18
 */
public class ExtensionFactory {

	private static ExtensionFactory factory;
	public static ExtensionFactory Default() {
		if(factory == null){
			factory = new ExtensionFactory();
		}
		return factory;
	}
	
	public List<Class> getTestExtention(){
		IConfigurationElement[] confElements =Platform.getExtensionRegistry().getConfigurationElementsFor("cn.com.zhongli.test.print");
		if(confElements != null && confElements.length > 0){
			 List<Class>  list = new ArrayList();
			for(int i = 0, h = confElements.length; i < h; i++){
				IConfigurationElement element = confElements[i];
				String name = element.getAttribute("ClassName");
				Class cla = new Class(name);
				String classPath = element.getAttribute ("Class");
				if (classPath != null && !"".equals(classPath)) {
					Object object = null;
					try {
						object = element.createExecutableExtension("Class");
					} catch (CoreException e) {
						e.printStackTrace();
					}
					if(object instanceof AbstractClassInformation){
						cla.setClassInfo((AbstractClassInformation) object);
					}
				}
				IConfigurationElement[] inputElements= element.getChildren("input");
				getStudent(cla, inputElements);
				list.add(cla);
			}
			return list;
		}
		return null;
	}
	
	private void getStudent(Class cla, IConfigurationElement[] inputElements){
		if(inputElements != null && inputElements.length > 0){
			Student[] stu = new Student[inputElements.length];
			for(int i = 0, h = inputElements.length; i < h; i++){
				Student one = new Student();
				one.setGroup(inputElements[i].getAttribute("Group"));
				one.setName(inputElements[i].getAttribute("Name"));
				one.setSex(inputElements[i].getAttribute("Sex"));
				one.setAge(inputElements[i].getAttribute("Age"));
				stu[i] = one;
			}
			cla.setStu(stu);
		}
	}
}
