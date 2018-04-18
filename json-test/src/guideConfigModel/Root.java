package guideConfigModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Root implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String type;
	// private List<Person> persons;
	// private Child child;
	// private String cmd;
//	private List<String> templateNames;
	private String callCommand;
	private String scope;
	private List<Page> pages;

	// public Child getChild() {
	// return child;
	// }
	//
	// public void setChild(Child child) {
	// this.child = child;
	// }

	public List<Page> getPages() {
		if (pages == null){
			pages = new ArrayList<>();
		}
		return pages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Root() {
		super();
	}

	public String getCallCommand() {
		return callCommand;
	}

	public void setCallCommand(String callCommand) {
		this.callCommand = callCommand;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @param id
	 * @param title
	 * @param type
	 * @param templateNames
	 * @param scope
	 */
	public Root(String id, String title, String type, String callCommand, String scope) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.scope = scope;
		this.callCommand = callCommand;
	}

}
