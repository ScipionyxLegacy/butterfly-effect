package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Worker;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IWorkerManagementService extends IService {

	public List<Worker> getAll() throws RestClientException, Exception;

}
