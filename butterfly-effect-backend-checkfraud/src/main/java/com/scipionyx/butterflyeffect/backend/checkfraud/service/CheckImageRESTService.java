package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scipionyx.butterflyeffect.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.checkfraud.model.ServiceConstants;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@RestController()
@MultipartConfig(fileSizeThreshold = 2097152)
public class CheckImageRESTService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckImageRESTService.class);

	@Autowired(required = true)
	private CheckImageService service;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = ServiceConstants.REST_IMAGE_PING, method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> ping() {
		LOGGER.info("Ping request.");
		return (new ResponseEntity<>("Hello", HttpStatus.OK));
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = ServiceConstants.REST_IMAGE_ANALYZE, method = RequestMethod.POST)
	public ResponseEntity<CheckImage> analyze(@RequestParam("image") MultipartFile image) throws IOException {
		CheckImage analyze = service.analyze(image.getBytes());
		return new ResponseEntity<>(analyze, HttpStatus.OK);
	}

}
