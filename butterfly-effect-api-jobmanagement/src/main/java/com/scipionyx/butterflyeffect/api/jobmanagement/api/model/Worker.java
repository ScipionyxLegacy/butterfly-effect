package com.scipionyx.butterflyeffect.api.jobmanagement.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author rmendes
 *
 */
public class Worker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String host;

	private List<String> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
