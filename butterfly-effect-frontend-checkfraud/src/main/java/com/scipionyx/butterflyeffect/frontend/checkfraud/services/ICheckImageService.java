package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import com.scipionyx.butterflyeffect.checkfraud.model.CheckImage;

/**
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public interface ICheckImageService {

	public CheckImage analyze();

	public String ping();

}
