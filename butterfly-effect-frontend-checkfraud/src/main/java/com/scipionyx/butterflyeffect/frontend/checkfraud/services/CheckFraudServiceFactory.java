package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckFraudServiceFactory {

	@Autowired
	private CheckImageRESTService checkImageRESTService;

	/**
	 * 
	 * 
	 * @return
	 */
	public final ICheckImageService instance() {
		return checkImageRESTService;
	}

}
