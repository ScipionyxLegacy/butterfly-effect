package com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 *            Service
 * @param <E>
 *            Entity
 */
public abstract class AbstractRestController<T extends IRepositoryService<ENTITY>, ENTITY> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestController.class);

	@Autowired
	protected transient T service;

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/ping", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<String> ping() throws RestClientException, Exception {
		LOGGER.debug("Ping request");
		return (new ResponseEntity<>(service.ping(), HttpStatus.OK));
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/health", method = { RequestMethod.GET, RequestMethod.POST })
	public final ResponseEntity<String> health() throws RestClientException, Exception {
		LOGGER.debug("Health request");
		return (new ResponseEntity<>(service.ping(), HttpStatus.OK));
	}

}
