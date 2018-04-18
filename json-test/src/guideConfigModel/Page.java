package guideConfigModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sequence;
	private List<PageControl> pageContents;



	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public List<PageControl> getPageContents() {
		if (pageContents == null) {
			pageContents = new ArrayList<>();
		}
		return pageContents;
	}

	public void setPageContents(List<PageControl> pageContents) {
		this.pageContents = pageContents;
	}

	public Page() {
		super();
	}

	public Page(String name) {
		super();
		this.sequence = name;
	}

}