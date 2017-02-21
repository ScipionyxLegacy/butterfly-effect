package com.scipionyx.butterflyeffect.backend.jobmanagement.service.job.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractRestController;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.management.Job;
import com.scipionyx.butterflyeffect.backend.jobmanagement.service.job.JobManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController
@RequestMapping("REST_SERVICES/scipionyx/jobmanagement/job/v1.0")
public class JobController extends AbstractRestController<JobManagementService, Job> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

	/**
	 * 
	 * @param job
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/post", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Job> post(final @RequestBody(required = true) Job job) throws RestClientException, Exception {
		LOGGER.debug("post");
		return (new ResponseEntity<>(service.post(job), HttpStatus.OK));
	}

	/**
	 * 
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	@RequestMapping(path = "/next", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Job> next() throws RestClientException, Exception {
		LOGGER.debug("next");
		return (new ResponseEntity<>(service.next(), HttpStatus.OK));
	}

}
