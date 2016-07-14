package com.scipionyx.butterflyeffect.backend.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.configuration.model.SystemConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 */
@RestController()
public class SystemConfigurationRESTService extends AbstractConfigurationRESTService<SystemConfiguration> {

	@Autowired
	private SystemConfigurationService configurationService;

	/**
	 * Rest Function
	 */
	@RequestMapping(value = "/bottomline/certmanager/systconfig/verify", method = RequestMethod.POST)
	public ResponseEntity<String> verify(@RequestBody(required = true) SystemConfiguration t) throws Exception {
		return createStringResponseEntity(configurationService.doVerify(t));
	}

	/**
	 * Rest Function
	 */
	@RequestMapping(value = "/bottomline/certmanager/systconfig/write", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody(required = true) SystemConfiguration systemConfiguration,
			String salt) throws Exception {
		return null; // createStringResponseEntity(configurationService.write(systemConfiguration,
						// true, null));
	}

	/**
	 * Rest Function
	 */
	@RequestMapping(value = "/bottomline/certmanager/systconfig/readAll", method = RequestMethod.GET)
	public ResponseEntity<List<SystemConfiguration>> readAll() throws Exception {
		return createListResponseEntity(configurationService.readAll());
	}

	@Override
	public AbstractConfigurationService<SystemConfiguration> getService() {
		// TODO Auto-generated method stub
		return null;
	}

}
