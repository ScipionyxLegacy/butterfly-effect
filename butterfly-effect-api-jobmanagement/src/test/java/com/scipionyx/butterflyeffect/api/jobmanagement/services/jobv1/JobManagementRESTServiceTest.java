package com.scipionyx.butterflyeffect.api.jobmanagement.services.jobv1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Job;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Priority;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.services.job.v1.JobManagementRESTClientService;

/**
 * 
 * @author Renato Mendes
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class JobManagementRESTServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobManagementRESTServiceTest.class);

	@Autowired
	private JobManagementRESTClientService service;

	/**
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@Test
	public void ping() throws RestClientException, Exception {
		String a = service.ping();
		Assert.assertNotNull(a);
		LOGGER.info("Test Ping:" + a);
	}

	/**
	 * 
	 * Send a Job with High Priority
	 * 
	 * @throws Exception
	 * @throws RestClientException
	 * 
	 */
	@Test
	public void post_01() throws RestClientException, Exception {

		Job job = new Job();

		job.setDescription("Just another job");
		job.setPriority(Priority.HIGH);
		job.setService(null);
		job.setWorkerGroup(null);

		job = service.post(job);

		Assert.assertNotNull(job);
		LOGGER.info("Test Ping:" + job);

	}

}
