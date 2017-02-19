package com.scipionyx.butterflyeffect.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
// @Component
// @EnableDiscoveryClient
public class Application {

	// @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
