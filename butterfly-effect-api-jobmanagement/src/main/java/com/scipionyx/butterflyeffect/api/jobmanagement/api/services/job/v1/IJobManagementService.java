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

	/**
	 * Get the next job to be processed.
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	public Job next() throws RestClientException, Exception;

	/**
	 * List of all jobs waiting on the queue.
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	public List<Job> get() throws RestClientException, Exception;

	public List<Job> get(Object status) throws RestClientException, Exception;

	public List<Job> get(Object status, Priority priority);

}
