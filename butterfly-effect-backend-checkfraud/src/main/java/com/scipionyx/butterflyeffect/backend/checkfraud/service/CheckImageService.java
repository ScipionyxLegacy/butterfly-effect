package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
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
	 * @param originalFileName
	 * @param contentType
	 * @param size
	 * @return
	 * @throws IOException
	 * @throws ImgIOException
	 */
	public final CheckImage analyze(byte[] bs, String originalFileName, long size, String contentType)
			throws IOException {

		LOGGER.info("Analyze executed");

		CheckImage checkImage = new CheckImage();

		checkImage.setImage(bs);
		checkImage.setOriginalFileName(originalFileName);
		checkImage.setSize(size);
		checkImage.setContentType(contentType);

		InputStream input = new ByteArrayInputStream(bs);
		MBFImage image = ImageUtilities.readMBF(input);

		checkImage.setColourSpace(image.getColourSpace());

		return checkImage;

	}

}
