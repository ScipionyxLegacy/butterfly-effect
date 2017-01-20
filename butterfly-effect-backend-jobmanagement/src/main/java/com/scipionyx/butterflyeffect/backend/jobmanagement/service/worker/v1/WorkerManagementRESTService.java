package com.scipionyx.butterflyeffect.backend.jobmanagement.service.worker.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.AbstractRESTServerService;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Worker;
import com.scipionyx.butterflyeffect.backend.jobmanagement.service.worker.WorkerManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController
@RequestMapping("REST_SERVICES/scipionyx/jobmanagement/worker/v1.0")
public class WorkerManagementRESTService extends AbstractRESTServerService<WorkerManagementService> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManagementRESTService.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<List<Worker>> getAll() {
		LOGGER.debug("getAll");
		List<Worker> list = service.getAll();
		return (new ResponseEntity<>(list, HttpStatus.OK));
	}

}
