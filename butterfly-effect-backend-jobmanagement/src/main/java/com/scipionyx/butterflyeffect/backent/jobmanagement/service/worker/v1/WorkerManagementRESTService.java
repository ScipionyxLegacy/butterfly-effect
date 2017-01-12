package com.scipionyx.butterflyeffect.backent.jobmanagement.service.worker.v1;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.Worker;
import com.scipionyx.butterflyeffect.backent.jobmanagement.service.worker.WorkerManagementService;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController()
@MultipartConfig(fileSizeThreshold = 2097152)
public class WorkerManagementRESTService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerManagementRESTService.class);

	@Autowired(required = true)
	private WorkerManagementService service;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> ping() {
		LOGGER.info("Ping request.");
		return (new ResponseEntity<>("Hello", HttpStatus.OK));
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<List<Worker>> getAll() throws IOException {
		LOGGER.info("getAll()");
		List<Worker> workers = service.getAll();
		return new ResponseEntity<>(workers, HttpStatus.OK);
	}

}
