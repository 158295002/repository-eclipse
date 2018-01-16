package cn.com.zhongli.test.bean;
import cn.com.zhongli.test.extension.bean.AbstractClassInformation;

/**
 * @author 钟利
 * @data 2012-12-18
 */
public class ClassInfo2 extends AbstractClassInformation {

	public ClassInfo2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String student_Count() {
		// TODO Auto-generated method stub
		return "30";
	}

	@Override
	public String class_Brief_Introduction() {
		return "这是女子班级";
	}

}
