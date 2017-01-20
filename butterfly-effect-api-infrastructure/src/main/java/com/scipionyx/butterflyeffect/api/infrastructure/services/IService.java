package com.scipionyx.butterflyeffect.api.infrastructure.services;

import org.springframework.web.client.RestClientException;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IService {

	public String ping() throws RestClientException, Exception;

	public String health() throws RestClientException, Exception;

}
