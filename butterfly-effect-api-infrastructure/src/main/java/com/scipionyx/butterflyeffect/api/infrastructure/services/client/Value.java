package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.io.Serializable;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class Value implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Operation operation;

	private Object value;

	public Value(){
	}
	
	public Value(Operation operation, Object value) {
		this.operation = operation;
		this.value = value;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}