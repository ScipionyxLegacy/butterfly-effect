package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @NotNull
	private String id;

	// @Nullable
	private String description;

	private String instructions;

	// @NotNull
	private Service service;

	/**
	 * When defined, only workers belonging to this workgroup will be
	 * considerate as candidates to execute this Job. If null, any workgroup
	 * will be entitled for executing the job
	 */
	// @Nullable
	private WorkerGroup workerGroup;

	// @NotNull
	private Date submitted;

	// @NotNull
	private long timeToLive;

	// @Nullable
	private Date dueDate;

	private Map<String, Object> parameters;

	private Priority priority;

	private int requiredCPUCount;

	private int requiredFreeMemory;

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

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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

	public long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getRequiredCPUCount() {
		return requiredCPUCount;
	}

	public void setRequiredCPUCount(int requiredCPUCount) {
		this.requiredCPUCount = requiredCPUCount;
	}

	public int getRequiredFreeMemory() {
		return requiredFreeMemory;
	}

	public void setRequiredFreeMemory(int requiredFreeMemory) {
		this.requiredFreeMemory = requiredFreeMemory;
	}

}
