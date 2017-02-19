package com.scipionyx.butterflyeffect.frontend.model;

import com.vaadin.server.Resource;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
// @JsonDeserialize(using = TitleDeserializer.class)
public class Title {

	private Resource icon;

	private String title;

	private String subTitle;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Resource getIcon() {
		return icon;
	}

	public void setIcon(Resource icon) {
		this.icon = icon;
	}

}