package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import org.springframework.stereotype.Component;

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
	public CheckImage analyze() {
		final String uri = baseUrl + ServiceConstants.REST_IMAGE_ANALYZE;
		return restTemplate.getForObject(uri, CheckImage.class);
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
