package com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author rmendes
 *
 */
public abstract class AbstractJobDefinition implements JobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	protected JobBuilderFactory jobBuilderFactory;

	@Autowired
	protected StepBuilderFactory stepBuilderFactory;

}
