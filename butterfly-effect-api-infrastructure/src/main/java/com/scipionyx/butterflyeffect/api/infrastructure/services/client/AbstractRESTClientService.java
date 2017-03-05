package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.RESTService;

/**
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractRESTClientService<ENTITY> implements IService<ENTITY> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TODO - does it need to be configurable ?
	 */
	private static final String SERVICE_DISCOVERY_NAME = "butterflyeffect-backend";

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRESTClientService.class);

	public final static String REST_BASE = "/REST_SERVICES/#vendor#/#module#/#sub-module#/#version#/#function#";

	@Autowired(required = true)
	protected transient RestTemplate restTemplate;

	@Autowired(required = true)
	protected transient DiscoveryClient discoveryClient;

	private String url;

	protected Class<ENTITY> clazz;
	protected Class<ENTITY[]> arrayClazz;

	/**
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	private final void init() throws Exception {

		RESTService annotation = this.getClass().getAnnotation(RESTService.class);

		if (annotation == null) {
			throw new Exception("The class [" + this.getClass().getName() + "] should be annotated with RESTService");
		}

		url = REST_BASE.replaceAll("#module#", annotation.module()).replaceAll("#sub-module#", annotation.subModule())
				.replaceAll("#version#", annotation.version()).replaceAll("#vendor#", annotation.vendor());

		// This code obtains what is the class that was provided as generic
		// parameter
		clazz = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		// clazz.getComponentType().getArr
		arrayClazz = (Class<ENTITY[]>) Array.newInstance(clazz, 0).getClass();

	}

	/**
	 * 
	 * TODO - Cache ?
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected URI calculateURI(final String function) throws Exception {

		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_DISCOVERY_NAME);

		LOGGER.debug("Number of available servers: {}", instances.size());

		if (instances.size() == 0) {
			throw new Exception("No servers available for the service [" + SERVICE_DISCOVERY_NAME + "]");
		}

		// TODO - Replace this by a better load balancers
		Random random = new Random();
		int index = random.nextInt(instances.size());

		// Calculate Service
		String service = url.replaceAll("#function#", function);
		LOGGER.debug("Service URL: {}", service);

		//
		return instances.get(index).getUri().resolve(service);

	}

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@Override
	public final String ping() throws RestClientException, Exception {
		return restTemplate.getForObject(calculateURI("ping"), String.class);
	}

	/**
	 * 
	 */
	@Override
	public final String health() throws RestClientException, Exception {
		return restTemplate.getForObject(calculateURI("health"), String.class);
	}

}
