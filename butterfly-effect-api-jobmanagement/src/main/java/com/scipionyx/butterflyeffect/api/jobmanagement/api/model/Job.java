package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Renato Mendes
 *
 */
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String id;

	@Nullable
	private String description;

	@NotNull
	private Service service;

	/**
	 * When defined, only workers belonging to this workgroup will be
	 * considerate as candidates to execute this Job. If null, any workgroup
	 * will be entitled for executing the job
	 */
	@Nullable
	private WorkerGroup workerGroup;

	@NotNull
	private Date submitted;

	@Nullable
	private Date dueDate;

	private Map<String, Object> parameters;

	private Priority priority;

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public WorkerGroup getWorkerGroup() {
		return workerGroup;
	}

	public void setWorkerGroup(WorkerGroup workerGroup) {
		this.workerGroup = workerGroup;
	}

}
