package com.scipionyx.butterflyeffect.backend.model.services.datasource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.backend.configuration.service.AbstractConfigurationRESTService;
import com.scipionyx.butterflyeffect.backend.configuration.service.AbstractConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datasource.ORMDataSource;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController
@RequestMapping("/rest/model/datasource")
public class ORMDataSourceConfigurationRESTService extends AbstractConfigurationRESTService<ORMDataSource> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ORMDataSourceConfigurationRESTService.class);

	/**
	 * 
	 * 
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public ResponseEntity<String> verify(@RequestBody(required = true) ORMDataSource dataSource) {
		LOGGER.debug("doVerify executed, dataSource:{}", dataSource);
		if (dataSource != null) {
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ResponseEntity<ORMDataSource> doWrite(@RequestBody(required = true) ORMDataSource dataSource) {
		LOGGER.debug("doWrite executed, dataSource:{}", dataSource);
		if (dataSource != null) {
			return new ResponseEntity<ORMDataSource>(HttpStatus.OK);
		} else {
			return new ResponseEntity<ORMDataSource>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/read/all", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<List<ORMDataSource>> readAll() {

		ResponseEntity<List<ORMDataSource>> responseEntity = new ResponseEntity<>(HttpStatus.OK);

		System.out.println("READ ALL");
		List<ORMDataSource> list = new ArrayList<>();
		list.add(new ORMDataSource());

		return responseEntity;
	}

	@Override
	public AbstractConfigurationService<ORMDataSource> getService() {
		// TODO Auto-generated method stub
		return null;
	}

}
