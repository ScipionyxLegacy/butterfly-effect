package com.scipionyx.butterflyeffect.model.model.datamodel;

import java.util.Set;

/**
 * 
 * @author Renato Mendes
 *
 */
public class Model {

	private String name;

	private String description;

	private Set<Entity> entities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Entity> getEntities() {
		return entities;
	}

	public void setEntities(Set<Entity> entities) {
		this.entities = entities;
	}

}
