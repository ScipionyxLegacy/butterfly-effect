package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Worker;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IWorkerManagementService extends IService<Worker> {

	public List<Worker> getAll() throws RestClientException, Exception;

}
