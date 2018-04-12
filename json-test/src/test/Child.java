package test;

import java.io.Serializable;

public class Child implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Child() {
		super();
	}

	public Child(int id) {
		super();
		this.id = id;
	}
	
	
}