package cn.com.zhongli.test.bean;
import cn.com.zhongli.test.extension.bean.AbstractClassInformation;

/**
 * @author 钟利
 * @data 2012-12-18
 */
public class ClassInfo1 extends AbstractClassInformation {

	public ClassInfo1() {
	}

	@Override
	public String student_Count() {
		return "20";
	}

	@Override
	public String class_Brief_Introduction() {
		return "这是成绩最好的班级";
	}

}
