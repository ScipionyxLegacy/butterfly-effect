package com.scipionyx.butterflyeffect.configuration.model.leftmenu;

import java.io.Serializable;

/**
 * 
 * @author Renato Mendes
 *
 */
public class LeftConfigurationMenuItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String label;

	private String shortLabel;

	private String view;

	private String parent;

	// private String description;

	private boolean separator;

	private boolean visible;

	private LeftConfigurationMenuItem parentItem;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public boolean isSeparator() {
		return separator;
	}

	public void setSeparator(boolean separator) {
		this.separator = separator;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public LeftConfigurationMenuItem getParentItem() {
		return parentItem;
	}

	public void setParentItem(LeftConfigurationMenuItem parentItem) {
		this.parentItem = parentItem;
	}

	public String getShortLabel() {
		return shortLabel;
	}

	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}

}
