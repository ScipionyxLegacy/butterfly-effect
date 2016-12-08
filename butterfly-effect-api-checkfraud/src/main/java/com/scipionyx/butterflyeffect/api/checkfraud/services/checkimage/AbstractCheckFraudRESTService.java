package com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 * @param <T>
 */
public abstract class AbstractCheckFraudRESTService<T> {

	@Autowired
	private EurekaClient discoveryClient;

	@Value("${butterflyeffect.secure.url:false}")
	private boolean secureUrl;

	protected RestTemplate restTemplate;

	/**
	 * 
	 * @param service
	 * @return
	 */
	protected String calculateUrl(final String service) {
		InstanceInfo nextServerFromEureka = discoveryClient.getNextServerFromEureka("BUTTERFLYEFFECT_BACKEND" + "",
				secureUrl);
		final String uri = nextServerFromEureka.getHomePageUrl() + service;
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
