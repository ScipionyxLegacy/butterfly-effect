package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.analysis.algorithm.histogram.HistogramAnalyser;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.resize.ResizeProcessor;
import org.openimaj.math.geometry.shape.Rectangle;
import org.openimaj.math.statistics.distribution.Histogram;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CheckImageService.class);

	private long SIGNATURE_X = 210;
	private long SIGNATURE_Y = 120;
	private long SIGNATURE_W = 190;
	private long SIGNATURE_H = 50;

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

		//
		InputStream input = new ByteArrayInputStream(bs);
		MBFImage image = ImageUtilities.readMBF(input);
		checkImage.setColourSpace(image.getColourSpace());

		MBFImage resize = resize(image);
		drawSignatureRetangle(resize);
		checkImage.setHistogram(histogram(resize));

		//
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageUtilities.write(resize, "png", output);

		checkImage.setImage(output.toByteArray());

		return checkImage;

	}

	/**
	 * Normalize the size of all check images
	 * 
	 * @param current
	 * @return
	 */
	private MBFImage resize(MBFImage current) {

		// Correct size do nothing
		if (current.getWidth() == 419)
			return current;

		// Shrink
		if (current.getWidth() > 419) {
			ResizeProcessor resize = new ResizeProcessor(419);

			MBFImage tmp = new MBFImage(419, 189, current.getColourSpace());
			tmp.fill(RGBColour.WHITE);

			MBFImage small = current.process(resize).normalise();

			return small;

		}

		return current;

	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	private MBFImage drawSignatureRetangle(MBFImage image) {

		Rectangle rectangle = new Rectangle();
		rectangle.setBounds(SIGNATURE_X, SIGNATURE_Y, SIGNATURE_W, SIGNATURE_H);

		image.drawShape(rectangle, 3, RGBColour.BLACK);

		return image;
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	private Histogram histogram(MBFImage image) {

		HistogramAnalyser analyser = new HistogramAnalyser(64);
		analyser.analyseImage(image.flatten());
		Histogram histogram = analyser.getHistogram();

		return histogram;
	}

}
