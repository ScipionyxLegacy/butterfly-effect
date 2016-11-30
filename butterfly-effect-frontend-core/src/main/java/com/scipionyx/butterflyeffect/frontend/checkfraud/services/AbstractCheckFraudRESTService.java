package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.GenericTypeResolver;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 * @param <T>
 */
public abstract class AbstractCheckFraudRESTService<T> {

	@Value(value = "${butterflyeffect.system.rest.url}")
	protected String baseUrl;

	protected final Class<T> genericType;

	protected RestTemplate restTemplate;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractCheckFraudRESTService() {
		super();
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
				AbstractCheckFraudRESTService.class);
		restTemplate = new RestTemplate();
	}

}
