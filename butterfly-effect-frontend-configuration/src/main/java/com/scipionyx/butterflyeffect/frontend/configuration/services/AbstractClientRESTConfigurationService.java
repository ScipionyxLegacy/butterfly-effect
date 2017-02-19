package com.scipionyx.butterflyeffect.frontend.configuration.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * 
 * 
 * @author renato mendes
 *
 */
public abstract class AbstractClientRESTConfigurationService<T extends IConfiguration> {

	@Autowired
	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	private final Class<T> genericType;

	public abstract String getRequestMapping();

	@SuppressWarnings("rawtypes")
	public abstract Class getArrayClass();

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractClientRESTConfigurationService() {
		super();
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
				AbstractClientRESTConfigurationService.class);
		restTemplate = new RestTemplate();
	}

	private String calculateBaseURL() {
		discoveryClient.getInstances("butterflyeffect_backend");
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T> getAll() throws Exception {

		final String uri = calculateBaseURL() + getRequestMapping() + "/getAll/";

		@SuppressWarnings("unchecked")
		ResponseEntity<T[]> responseEntity = restTemplate.getForEntity(uri, getArrayClass());

		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new Exception("Ops Something wrong");
		}

		//
		// List<T> response = new ArrayList<>();
		T[] array = responseEntity.getBody();
		@SuppressWarnings("unchecked")
		List<T> response = CollectionUtils.arrayToList(array);
		//
		return response;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final T read(T t, String salt) {
		final String uri = calculateBaseURL() + getRequestMapping() + "read/{id}/{salt}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", t.getId());
		uriVariables.put("salt", salt);
		T body = restTemplate.getForEntity(uri, genericType, uriVariables).getBody();
		return body;
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final T put(T t) {
		final String uri = calculateBaseURL() + normalizeRequestMapping() + "put/";
		restTemplate.put(uri, t);
		return null;
	}

	/**
	 * it should end with "/"
	 * 
	 * @return
	 */
	private final String normalizeRequestMapping() {
		// Verify
		String requestMapping = getRequestMapping();
		if (!requestMapping.endsWith("/")) {
			requestMapping = requestMapping + "/";
		}
		if (requestMapping.startsWith("/")) {
			requestMapping = requestMapping.replaceFirst("/", "");
		}
		return requestMapping;
	}

	/**
	 * 
	 * @param url
	 * @param t
	 * @return
	 */
	public final void delete(T t) {
		final String uri = calculateBaseURL() + normalizeRequestMapping() + t.getId();
		restTemplate.delete(uri);
	}

	/**
	 * 
	 * @param url
	 * @param t
	 * @return
	 */
	public final void deleteAll(T t) {
		final String uri = calculateBaseURL() + normalizeRequestMapping() + "all";
		restTemplate.delete(uri);
	}

}
