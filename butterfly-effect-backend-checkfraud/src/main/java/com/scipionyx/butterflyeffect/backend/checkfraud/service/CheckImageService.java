package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.checkfraud.model.CheckImage;

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
	 * @param bs
	 * @return
	 */
	public final CheckImage analyze(byte[] bs) {
		LOGGER.info("Analyze executed");
		CheckImage checkImage = new CheckImage();
		checkImage.setImage(bs);
		return checkImage;
	}

}
