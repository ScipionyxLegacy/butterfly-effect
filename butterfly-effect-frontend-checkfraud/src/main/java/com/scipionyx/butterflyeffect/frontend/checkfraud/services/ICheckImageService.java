package com.scipionyx.butterflyeffect.frontend.checkfraud.services;

import java.io.File;

import com.scipionyx.butterflyeffect.checkfraud.model.CheckImage;

/**
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public interface ICheckImageService {

	public String ping();

	public CheckImage analyze(File file);

}
