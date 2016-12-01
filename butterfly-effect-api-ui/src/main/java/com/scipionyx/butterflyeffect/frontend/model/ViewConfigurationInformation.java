package com.scipionyx.butterflyeffect.frontend.model;

import com.scipionyx.butterflyeffect.frontend.model.Title;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public class ViewConfigurationInformation {

	private String version;

	private Title leftTitle;

	private Title title;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Title getLeftTitle() {
		return leftTitle;
	}

	public void setLeftTitle(Title leftTitle) {
		this.leftTitle = leftTitle;
	}

}
