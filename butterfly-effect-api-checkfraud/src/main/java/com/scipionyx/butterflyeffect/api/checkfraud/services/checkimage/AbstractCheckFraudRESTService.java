package com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 * @param <T>
 */
public abstract class AbstractCheckFraudRESTService<T> {

	@Value("${butterflyeffect.secure.url:false}")
	private boolean secureUrl;

	protected RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 
	 * @param service
	 * @return
	 */
	protected String calculateUrl(final String service) {
		List<ServiceInstance> instances = discoveryClient.getInstances("butterflyeffect-backend");
		final String uri = "http://" + instances.get(0).getHost() + ":" + instances.get(0).getPort() + service;
		return uri;
	}

	/**
	 * 
	 */
	public AbstractCheckFraudRESTService() {
		super();
		restTemplate = new RestTemplate();
	}

}
