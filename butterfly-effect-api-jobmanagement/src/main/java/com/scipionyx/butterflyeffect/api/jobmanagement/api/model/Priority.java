package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public enum Priority {

	LOW(1), NORMAL(3), HIGH(5), URGENT(7), EXTREME(9);

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
