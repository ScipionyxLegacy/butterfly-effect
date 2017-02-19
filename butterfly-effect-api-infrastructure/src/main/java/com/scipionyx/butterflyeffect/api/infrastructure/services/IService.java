package com.scipionyx.butterflyeffect.api.infrastructure.services;

import java.io.Serializable;

import org.springframework.web.client.RestClientException;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IService extends Serializable {

	public String ping() throws RestClientException, Exception;

	public String health() throws RestClientException, Exception;

}
