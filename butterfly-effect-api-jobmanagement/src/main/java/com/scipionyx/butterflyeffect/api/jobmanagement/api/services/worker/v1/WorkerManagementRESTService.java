package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.AbstractRESTService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Worker;

/**
 * 
 * @author Renato Mendes
 *
 */
@Component
public class WorkerManagementRESTService extends AbstractRESTService implements IWorkerManagementService {

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@Override
	public String ping() throws RestClientException, Exception {
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(calculateUrl(""), null, String.class);
		return postForEntity.getBody();
	}

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Worker> getAll() throws RestClientException, Exception {
		ResponseEntity<List> postForEntity = restTemplate.postForEntity(calculateUrl(""), null, List.class);
		return postForEntity.getBody();
	}

}
