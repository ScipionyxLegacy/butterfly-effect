package com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author rmendes
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Definition {

	String description() default "";

	String instuctions();

	String service();

	String category();

	String name();

	Class<?> restController();

	Parameter[] parameters();

	/**
	 * 
	 * @author Renato Mendes
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Parameter {

		String name();

		Class<?> type();

		String description();

	}

}
