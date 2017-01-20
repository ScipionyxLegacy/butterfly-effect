package com.scipionyx.butterflyeffect.api.checkfraud.services.checkimage;

import java.io.File;
import java.io.IOException;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;
import com.scipionyx.butterflyeffect.api.checkfraud.model.TrainCheckImage;

/**
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public interface ICheckImageService {

	public String ping();

	public CheckImage analyze(File file);

	public CheckImage analyze(String fileName, byte[] bs) throws IOException;

	public TrainCheckImage trainPreview(TrainCheckImage trainCheckImage) throws IOException;

}
