package com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition;

/**
 * 
 * @author rmendes
 *
 */
public @interface Definition {

	String description() default "";

	String instuctions();

	String service();

	String category();

}
