package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IHasRepository;
import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;

/**
 * 
 * This is the abstract Data Access services.
 * 
 * 
 * @author Renato Mendes
 *
 * @param <ENTITY>
 * @param <REPOSITORY>
 */
public abstract class AbstractDataAccessService<ENTITY, REPOSITORY>
		implements IService<ENTITY>, IHasRepository<ENTITY, REPOSITORY> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private CrudRepository<ENTITY, Long> repository;

	/**
	 * 
	 */
	@Override
	public String ping() throws RestClientException, Exception {
		return "I'm good, and what about u ? " + (repository != null);
	}

	/**
	 * 
	 */
	@Override
	public String health() throws RestClientException, Exception {
		return "ok";
	}

	/**
	 * 
	 */
	public CrudRepository<ENTITY, Long> getRepository() {
		return repository;
	}

}
