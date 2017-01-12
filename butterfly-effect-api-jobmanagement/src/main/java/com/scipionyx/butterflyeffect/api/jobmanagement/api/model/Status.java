package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

/**
 * 
 * @author Renato Mendes
 *
 */
public enum Status {

	/**
	 * This job is waiting on the queue
	 */
	ENQUEUED,

	/**
	 * This job was completed with Error
	 */
	ERROR,

	/**
	 * Job Completed successfully
	 */
	COMPLETED,

	/**
	 * Running
	 */
	RUNNING

}
