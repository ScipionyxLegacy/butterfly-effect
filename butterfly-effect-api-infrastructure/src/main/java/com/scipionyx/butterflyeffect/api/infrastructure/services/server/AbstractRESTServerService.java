package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IHasRepository;
import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractRESTServerService<T extends IService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRESTServerService.class);

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

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/findAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Iterable<T>> findAll() throws RestClientException, Exception {
		LOGGER.debug("Health request");
		if (service instanceof IHasRepository) {
			@SuppressWarnings("unchecked")
			CrudRepository<T, Long> repository = ((IHasRepository<T>) service).getRepository();
			Iterable<T> findAll = repository.findAll();
			return (new ResponseEntity<>(findAll, HttpStatus.OK));
		}
		return (new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
	}

}
