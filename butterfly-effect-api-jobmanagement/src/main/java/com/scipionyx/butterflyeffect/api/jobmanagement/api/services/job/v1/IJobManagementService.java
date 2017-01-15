package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.job.v1;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Priority;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IJobManagementService extends IService {

	/**
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	public Job post(Job job) throws RestClientException, Exception;

	public Job next();

	public List<Job> listAll();

	public List<Job> listAll(Object status);

	public void listAllActive(Priority priority);

}
