package com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage;

import org.springframework.beans.factory.annotation.Value;
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

	protected RestTemplate restTemplate;

	/**
	 * 
	 */
	public AbstractCheckFraudRESTService() {
		super();
		restTemplate = new RestTemplate();
	}

}
