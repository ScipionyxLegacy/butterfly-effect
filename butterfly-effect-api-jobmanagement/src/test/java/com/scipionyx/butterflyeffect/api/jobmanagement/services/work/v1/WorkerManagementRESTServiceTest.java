package com.scipionyx.butterflyeffect.api.jobmanagement.services.work.v1;

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

import com.scipionyx.butterflyeffect.api.jobmanagement.api.services.worker.v1.WorkerManagementRESTClientService;

/**
 * 
 * @author Renato Mendes
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {})
public class WorkerManagementRESTServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManagementRESTServiceTest.class);

	@Autowired
	private WorkerManagementRESTClientService service;

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

}
