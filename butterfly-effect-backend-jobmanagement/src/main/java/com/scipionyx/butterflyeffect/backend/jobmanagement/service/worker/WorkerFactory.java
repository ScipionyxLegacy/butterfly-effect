package com.scipionyx.butterflyeffect.backend.jobmanagement.service.worker;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;

/**
 * 
 * @author Renato Mendes
 *
 */
@Component
public class WorkerFactory {

	/**
	 * 
	 * @param user
	 * @return
	 * @throws InterruptedException
	 */
	public Future<Job> next() throws InterruptedException {
		Job results = null;
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		return new AsyncResult<>(results);
	}

	public String process() {
		return "";
	}

}
