package guideConfigModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageControl implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String controlType;
	private String controlLabel;
	private String styleType;
	private boolean isContainer;
	private List<PageControl> childControls;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getControlType() {
		return controlType;
	}


	public void setControlType(String controlType) {
		this.controlType = controlType;
	}


	public String getControlLabel() {
		return controlLabel;
	}


	public void setControlLabel(String controlLabel) {
		this.controlLabel = controlLabel;
	}


	public String getStyleType() {
		return styleType;
	}


	public void setStyleType(String styleType) {
		this.styleType = styleType;
	}


	public boolean isContainer() {
		return isContainer;
	}


	public void setContainer(boolean isContainer) {
		this.isContainer = isContainer;
	}


	public List<PageControl> getChildControls() {
		if(childControls == null) {
			childControls = new ArrayList<>();
		}
		return childControls;
	}


	public void setChildControls(List<PageControl> childControls) {
		this.childControls = childControls;
	}


	public PageControl() {
		super();
	}


	public PageControl(String id, String controlType, String label, String styleType) {
		super();
		this.id = id;
		this.controlType = controlType;
		this.controlLabel = label;
		this.styleType = styleType;
	}



	
	
}
