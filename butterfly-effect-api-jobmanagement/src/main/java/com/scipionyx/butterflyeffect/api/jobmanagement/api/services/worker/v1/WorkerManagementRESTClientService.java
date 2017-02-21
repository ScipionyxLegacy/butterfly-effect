package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Worker;

/**
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "jobmanagement", subModule = "worker", version = "v1.0")
public class WorkerManagementRESTClientService extends AbstractRESTClientWithCrudService<Worker> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
