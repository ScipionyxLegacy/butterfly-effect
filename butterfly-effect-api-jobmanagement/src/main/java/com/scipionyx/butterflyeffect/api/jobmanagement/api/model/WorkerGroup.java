package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Renato Mendes
 *
 */
public class WorkerGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private List<Worker> workers;

	private WorkerStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public WorkerStatus getStatus() {
		return status;
	}

	public void setStatus(WorkerStatus status) {
		this.status = status;
	}

}
