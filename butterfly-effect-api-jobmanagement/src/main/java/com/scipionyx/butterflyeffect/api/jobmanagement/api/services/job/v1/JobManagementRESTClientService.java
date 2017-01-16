package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.job.v1;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Priority;

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
		return restTemplate.getForObject(calculateURI("post"), Job.class);
	}

	@Override
	public Job next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> listAll(Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listAllActive(Priority priority) {
		// TODO Auto-generated method stub

	}

}
