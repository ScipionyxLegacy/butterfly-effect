package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.checkfraud.model.ServiceConstants;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController()
public class CheckImageRESTService {

	@Autowired(required = true)
	private CheckImageRESTService service;

	@RequestMapping(value = ServiceConstants.CHECK_IMAGE_ANALYZE, method = RequestMethod.POST)
	public ResponseEntity<String> analyze() {
		return service.analyze();
	}

}
