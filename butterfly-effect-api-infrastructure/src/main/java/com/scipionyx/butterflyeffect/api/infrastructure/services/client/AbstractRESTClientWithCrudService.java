package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.net.URI;

import org.springframework.web.client.RestClientException;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 */
public abstract class AbstractRESTClientWithCrudService<T> extends AbstractRESTClientService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Iterable<T> findAll() throws RestClientException, Exception {
		URI calculateURI = calculateURI("findAll");
		return restTemplate.getForObject(calculateURI, Iterable.class);
	}

}
