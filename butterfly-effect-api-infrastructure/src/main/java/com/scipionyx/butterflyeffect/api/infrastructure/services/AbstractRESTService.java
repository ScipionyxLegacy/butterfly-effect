package com.scipionyx.butterflyeffect.api.infrastructure.services;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Renato Mendes
 *
 */
public class AbstractRESTService {

	/**
	 * TODO - Is this need to be configurable ?
	 */
	private static final String SERVICE_DISCOVERY_NAME = "butterflyeffect-backend";

	@Autowired
	protected transient RestTemplate restTemplate;

	@Autowired
	protected transient DiscoveryClient discoveryClient;

	/**
	 * 
	 * TODO - Cache ?
	 * 
	 * @param service
	 * @return
	 * @throws Exception
	 */
	protected URI calculateUrl(final String service) throws Exception {

		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_DISCOVERY_NAME);

		if (instances.size() == 0) {
			throw new Exception("No servers available for the service [" + SERVICE_DISCOVERY_NAME + "]");
		}

		// TODO - Replace this by a better load balancers
		Random random = new Random();
		int index = random.nextInt(instances.size());

		return instances.get(index).getUri().resolve(service);

		// final String uri = "http://" + instances.get(index).getHost() + ":" +
		// instances.get(index).getPort() + service;

		// return uri;
	}

}
