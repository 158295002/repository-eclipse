package cn.com.zhongli.test.extension.bean;
/**inputԪ�ض�����
 * @author ����
 * @data 2012-12-18
 */
public class Student {
	private String name;
	private String sex;
	private String age;
	private String group;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "ѧ����" + name + "    �Ա�" + sex + "    ���䣺" + age +  "    �꼶��" + group;
	}
}
