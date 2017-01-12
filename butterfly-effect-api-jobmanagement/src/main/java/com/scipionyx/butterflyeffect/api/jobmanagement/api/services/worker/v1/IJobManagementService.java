package com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1;

import java.util.List;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Priority;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IJobManagementService {

	/**
	 * 
	 */
	public void postJob(Job job);

	public List<Job> getAll();

	public List<Job> getAll(Object status);

	public void getAllActive(Priority priority);

}
