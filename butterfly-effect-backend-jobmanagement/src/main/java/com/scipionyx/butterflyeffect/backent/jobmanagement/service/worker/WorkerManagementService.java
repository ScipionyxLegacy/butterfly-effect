package com.scipionyx.butterflyeffect.backent.jobmanagement.service.worker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Worker;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1.IWorkerManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class WorkerManagementService implements IWorkerManagementService {

	private static final String SERVICE_DISCOVERY_NAME = "butterflyeffect-backend";

	@Autowired
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
		// TODO Auto-generated method stub
		return null;
	}

}
