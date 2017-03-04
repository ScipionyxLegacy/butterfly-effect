package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.io.Serializable;

/**
 * 
 * @author rmendes
 *
 * @deprecated - use maps
 *
 */
@Deprecated
public class CrudParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String field;

	private Object Value;

	public CrudParameter(String field, Object value) {
		this.field = field;
		this.Value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return Value;
	}

	public void setValue(Object value) {
		Value = value;
	}

}