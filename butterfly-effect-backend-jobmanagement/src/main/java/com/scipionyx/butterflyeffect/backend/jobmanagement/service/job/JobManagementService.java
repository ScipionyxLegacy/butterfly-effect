package com.scipionyx.butterflyeffect.backend.jobmanagement.service.job;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.jms.core.JmsTemplate;
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

	@Autowired(required = true)
	private transient JmsTemplate jmsTemplate;

	/**
	 * 
	 */
	@Override
	public String ping() {
		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_DISCOVERY_NAME);
		String message = "Server up, [" + instances.size() + "] servers available";
		return message;
	}

	/**
	 * 
	 */
	@Override
	public String health() throws RestClientException, Exception {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Job post(Job job) throws RestClientException, Exception {

		// TODO - Check Conditions

		job.setId(UUID.randomUUID().toString());
		job.setSubmitted(Calendar.getInstance().getTime());

		if (job.getPriority() == null) {
			job.setPriority(Priority.NORMAL);
		}

		if (job.getTimeToLive() == 0) {
			job.setTimeToLive(Long.MAX_VALUE);
		}

		//
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setTimeToLive(job.getTimeToLive());
		jmsTemplate.setPriority(job.getPriority().getValue());

		//
		jmsTemplate.setDefaultDestinationName("jobs-queue");
		jmsTemplate.convertAndSend(job);

		return job;
	}

	@Override
	public Job next() {
		return null;
	}

	@Override
	public List<Job> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> get(Object status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> get(Object status, Priority priority) {
		// TODO Auto-generated method stub
		return null;
	}

}
