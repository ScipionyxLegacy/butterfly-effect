package com.scipionyx.butterflyeffect.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO - verify all the services available on the system
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController()
public class Health {

	private static final Logger LOGGER = LoggerFactory.getLogger(Health.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "health", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> health() {
		LOGGER.debug("health request");
		return (new ResponseEntity<>("System up", HttpStatus.OK));
	}

}
