package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

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

	private Date submitted;

	private Map<String, Object> parameters;

	private String description;

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

}
