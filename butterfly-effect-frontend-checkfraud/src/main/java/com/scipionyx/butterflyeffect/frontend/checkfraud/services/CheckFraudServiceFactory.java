package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class CheckFraudServiceFactory {

	/**
	 * 
	 * 
	 * @return
	 */
	public final ICheckImageService instance() {
		return new CheckImageService();
	}

}
