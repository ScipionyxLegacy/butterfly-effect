package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;

/**
 * 
 * @author Renato Mendes
 *
 */
public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String description;

	private Parallelism parallelism;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Parallelism getParallelism() {
		return parallelism;
	}

	public void setParallelism(Parallelism parallelism) {
		this.parallelism = parallelism;
	}

}
