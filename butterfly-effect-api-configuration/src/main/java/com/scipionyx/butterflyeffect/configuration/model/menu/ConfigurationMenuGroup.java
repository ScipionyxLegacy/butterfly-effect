package com.scipionyx.butterflyeffect.configuration.model.menu;

import java.util.List;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class ConfigurationMenuGroup {

	private String name;

	private String display;

	private List<ConfigurationMenuItem> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<ConfigurationMenuItem> getItems() {
		return items;
	}

	public void setItems(List<ConfigurationMenuItem> items) {
		this.items = items;
	}

}
