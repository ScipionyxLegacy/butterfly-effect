package com.scipionyx.butterflyeffect.api.infrastructure.services.server.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IRepositoryService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IService;

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
public abstract class AbstractElasticsearchAccessService<ENTITY, REPOSITORY>
		implements IService<ENTITY>, IRepositoryService<ENTITY> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private ElasticsearchRepository<ENTITY, Long> repository;

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
	public ElasticsearchRepository<ENTITY, Long> getRepository() {
		return repository;
	}

}
