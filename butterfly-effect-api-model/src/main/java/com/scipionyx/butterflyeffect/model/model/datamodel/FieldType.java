package com.scipionyx.butterflyeffect.model.model.datamodel;

import java.util.Date;

/**
 * 
 * @author Renato Mendes
 *
 */
public enum FieldType {

	//
	STRING("String", "Text", String.class),
	//
	INTEGER("Integer", "Integer", Integer.class),
	//
	DOUBLE("Integer", "Integer", Double.class),
	//
	DATE("Integer", "Integer", Date.class),
	//
	CUSTOM("Integer", "Integer", null);

	//

	private final String name;
	private final String label;
	private final Class<?> clazz;

	FieldType(String name, String label, Class<?> clazz) {
		this.name = name;
		this.label = label;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
