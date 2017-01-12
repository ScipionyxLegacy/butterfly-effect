package com.scipionyx.butterflyeffect.api.infrastructure.services;

import org.springframework.web.client.RestClientException;

/**
 * 
 * @author rmendes
 *
 */
public interface IService {

	public String ping() throws RestClientException, Exception;

}
