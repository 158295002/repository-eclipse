package test;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
//	private List<Person> persons;
//	private Child child;
	private List<Child> childs;
    

//	public Child getChild() {
//		return child;
//	}
//
//	public void setChild(Child child) {
//		this.child = child;
//	}

	public int getId() {
		return id;
	}

	public List<Child> getChilds() {
		return childs;
	}

	public void setChilds(List<Child> childs) {
		this.childs = childs;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person() {
		super();
	}

	public Person(int id, String name,List<Child> childs) {
		super();
		this.id = id;
		this.name = name;
		this.childs = childs;
	}
	
}
