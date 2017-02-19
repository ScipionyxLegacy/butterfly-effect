package com.scipionyx.butterflyeffect.backend.jobmanagement.service.worker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Worker;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1.IWorkerManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
public class WorkerManagementService implements IWorkerManagementService {

	private static final String SERVICE_DISCOVERY_NAME = "butterflyeffect-backend";

	@Autowired(required = true)
	private transient DiscoveryClient discoveryClient;

	/**
	 * 
	 */
	@Override
	public List<Worker> getAll() {

		List<Worker> workers = new ArrayList<>();

		List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_DISCOVERY_NAME);

		for (ServiceInstance serviceInstance : instances) {
			Worker worker = new Worker();
			worker.setId(serviceInstance.getServiceId());
			worker.setHost(serviceInstance.getHost());
			workers.add(worker);
		}

		return workers;
	}

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

}
