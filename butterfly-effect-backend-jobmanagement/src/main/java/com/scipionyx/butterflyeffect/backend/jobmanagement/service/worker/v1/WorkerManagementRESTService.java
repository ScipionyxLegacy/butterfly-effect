package com.scipionyx.butterflyeffect.backend.jobmanagement.service.worker.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController
public class WorkerManagementRESTService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManagementRESTService.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "REST_SERVICES/scipionyx/jobmanagement/worker/ping/v1.0", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ResponseEntity<String> ping() {
		LOGGER.info("Ping request.");
		return (new ResponseEntity<>("Hello", HttpStatus.OK));
	}

}
