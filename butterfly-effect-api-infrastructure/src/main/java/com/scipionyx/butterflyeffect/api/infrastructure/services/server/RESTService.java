package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Renato Mendes
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RESTService {

	/**
	 * API Version
	 * 
	 * @return
	 */
	String version() default "v1";

	String vendor() default "scipionyx";

	String module();

	String subModule();

}
