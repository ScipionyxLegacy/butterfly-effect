package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public enum Priority {

	LOW(1), NORMAL(2), HIGH(3), URGENT(4);

	private final int value;

	/**
	 * 
	 * @param value
	 */
	private Priority(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
