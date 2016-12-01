package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.scipionyx.butterflyeffect.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.checkfraud.model.ServiceConstants;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageRESTService extends AbstractCheckFraudRESTService<CheckImage> implements ICheckImageService {

	/**
	 * 
	 */
	@Override
	public CheckImage analyze(File file) {

		final String uri = baseUrl + ServiceConstants.REST_IMAGE_ANALYZE;

		FileSystemResource fileSystemResource = new FileSystemResource(file);

		// MultipartRequest
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("image", fileSystemResource);

		//
		return restTemplate.postForObject(uri, parts, CheckImage.class);
	}

	/**
	 * 
	 */
	@Override
	public String ping() {
		final String uri = baseUrl + ServiceConstants.REST_IMAGE_PING;
		return restTemplate.getForObject(uri, String.class);
	}

}
