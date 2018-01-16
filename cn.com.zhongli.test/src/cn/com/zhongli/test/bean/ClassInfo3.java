package cn.com.zhongli.test.bean;

import cn.com.zhongli.test.extension.bean.AbstractClassInformation;
/**
 * @author 钟利
 * @data 2012-12-18
 */
public class ClassInfo3 extends AbstractClassInformation {

	public ClassInfo3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String student_Count() {
		return "10";
	}

	@Override
	public String class_Brief_Introduction() {
		return "这个是有问题的班级";
	}

}
