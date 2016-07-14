package com.scipionyx.butterflyeffect.model.model.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.scipionyx.butterflyeffect.configuration.model.AbstractConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 */
public class Entity extends AbstractConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	private String packageName;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String label;

	private List<Field> fields;

	private Class<?> clazz;

	@NotNull
	private EntityType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
