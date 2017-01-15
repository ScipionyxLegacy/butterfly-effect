package com.scipionyx.butterflyeffect.backend.jobmanagement.service.job.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.AbstractRESTServerService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Job;
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
public class JobManagementRESTService extends AbstractRESTServerService<JobManagementService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobManagementRESTService.class);

	/**
	 * 
	 * @param job
	 * @return
	 * @throws Exception
	 * @throws RestClientException
	 */
	@RequestMapping(path = "/post", method = { RequestMethod.POST, RequestMethod.POST })
	public ResponseEntity<String> post(Job job) throws RestClientException, Exception {
		LOGGER.debug("getAll");
		service.post(job);
		return (new ResponseEntity<>(HttpStatus.OK));
	}

}
