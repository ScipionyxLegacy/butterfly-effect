package com.scipionyx.butterflyeffect.frontend.model;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class Navigation {

	private String id;

	private String view;

	private String label;

	private String parent;

	private Boolean separator;

	private Boolean visible;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Boolean getSeparator() {
		return separator;
	}

	public void setSeparator(Boolean separator) {
		this.separator = separator;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
