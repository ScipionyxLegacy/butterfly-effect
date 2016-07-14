package com.scipionyx.butterflyeffect.backend.configuration.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scipionyx.butterflyeffect.backend.configuration.api.IResponse;
import com.scipionyx.butterflyeffect.backend.configuration.api.IVerifyRESTService;
import com.scipionyx.butterflyeffect.backend.configuration.api.IWriteRESTService;
import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 * @param <T>
 */
public abstract class AbstractConfigurationRESTService<T extends IConfiguration>
		implements IWriteRESTService<T>, IVerifyRESTService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationRESTService.class);

	public abstract AbstractConfigurationService<T> getService();

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	@RequestMapping(value = "/ping", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<String> ping() throws Exception {
		LOGGER.debug("Ping Executed");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	@RequestMapping(value = "/put", method = { RequestMethod.PUT })
	public ResponseEntity<String> put(@RequestBody(required = true) T t) throws Exception {
		getService().put(t, true, "salt");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> get(@RequestBody(required = true) T t) throws Exception {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> getAll() throws Exception {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected ResponseEntity<String> createStringResponseEntity(IResponse<?> response) throws Exception {
		HttpStatus httpStatus = (response.isVerified()) ? httpStatus = HttpStatus.OK : HttpStatus.BAD_REQUEST;
		String message = (response.isVerified()) ? "Ok" : response.getException().getMessage();
		return new ResponseEntity<>(message, httpStatus);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected ResponseEntity<T> createObjectResponseEntity(IResponse<T> response) throws Exception {
		HttpStatus httpStatus = (response.isVerified()) ? httpStatus = HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(response.getResponse(), httpStatus);
	}

	/**
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected ResponseEntity<List<T>> createListResponseEntity(IResponse<List<T>> response) throws Exception {
		HttpStatus httpStatus = (response.isVerified()) ? httpStatus = HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(response.getResponse(), httpStatus);
	}

}
