package com.scipionyx.butterflyeffect.backend.jobmanagement.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Priority;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.services.job.v1.IJobManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
public class JobManagementService implements IJobManagementService {

	private static final String SERVICE_DISCOVERY_NAME = "butterflyeffect-backend";

	@Autowired(required = true)
	private transient DiscoveryClient discoveryClient;

	@Override
	public String ping() {
		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_DISCOVERY_NAME);
		String message = "Server up, [" + instances.size() + "] servers available";
		return message;
	}

	@Override
	public String health() throws RestClientException, Exception {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Job post(Job job) throws RestClientException, Exception {
		// TODO Auto-generated method stub
		// TODO - Check Conditions
		// TODO - Post Job on ActiveMQ
		// TODO - Return the Job with remaining information
		return job;
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
