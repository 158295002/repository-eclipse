package cn.com.zhongli.test.extension.bean;
/**outputԪ�ض�����
 * @author ����
 * @data 2012-12-18
 */
public class Class {
	private String name;
	private AbstractClassInformation classInfo;
	private Student[] stu;
	
	public Class(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setClassInfo(AbstractClassInformation classInfo) {
		this.classInfo = classInfo;
	}
	
	public AbstractClassInformation getClassInfo() {
		return classInfo;
	}
	
	public void setStu(Student[] stu) {
		this.stu = stu;
	}
	
	public Student[] getStu() {
		return stu;
	}

}
