package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Worker;

/**
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "jobmanagement", subModule = "worker", version = "v1.0")
public class WorkerManagementRESTClientService extends AbstractRESTClientService implements IWorkerManagementService {

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Worker> getAll() throws RestClientException, Exception {
		ResponseEntity<List> postForEntity = restTemplate.postForEntity(calculateURI("getAll"), null, List.class);
		return postForEntity.getBody();
	}

}
