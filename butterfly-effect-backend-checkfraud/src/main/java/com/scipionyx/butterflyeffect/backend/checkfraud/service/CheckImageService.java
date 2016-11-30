package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CheckImageService.class);

	/**
	 * 
	 * @return
	 */
	public final String analyze() {
		LOGGER.info("Analyze executed");
		return null;
	}

}
