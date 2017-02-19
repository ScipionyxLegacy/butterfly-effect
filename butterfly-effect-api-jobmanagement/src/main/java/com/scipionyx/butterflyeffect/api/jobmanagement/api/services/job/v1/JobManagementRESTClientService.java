package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.job.v1;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Priority;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "jobmanagement", subModule = "job", version = "v1.0")
public class JobManagementRESTClientService extends AbstractRESTClientService implements IJobManagementService {

	/**
	 * 
	 */
	@Override
	public Job post(Job job) throws RestClientException, Exception {
		return restTemplate.postForObject(calculateURI("post"), job, Job.class);
	}

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@Override
	public Job next() throws RestClientException, Exception {
		return restTemplate.postForObject(calculateURI("next"), null, Job.class);
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Job> get() throws RestClientException, Exception {
		return restTemplate.postForObject(calculateURI("get"), null, List.class);
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Job> get(Object status) throws RestClientException, Exception {
		return restTemplate.postForObject(calculateURI("get"), null, List.class);
	}

	@Override
	public List<Job> get(Object status, Priority priority) {
		// TODO Auto-generated method stub
		return null;
	}

}
