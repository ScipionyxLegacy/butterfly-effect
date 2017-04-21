package com.scipionyx.butterflyeffect.api.infrastructure.services.server.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.client.RestClientException;

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
public abstract class AbstractJpaAccessService<ENTITY, REPOSITORY> extends AbstractAccessService<ENTITY, REPOSITORY> {

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
	public String doPing() throws RestClientException, Exception {
		return "I'm good, and what about u ? " + (repository != null);
	}

	/**
	 * 
	 */
	public CrudRepository<ENTITY, Long> getRepository() {
		return repository;
	}

}
