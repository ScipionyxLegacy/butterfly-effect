package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.analysis.algorithm.histogram.HistogramAnalyser;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.resize.ResizeProcessor;
import org.openimaj.math.geometry.shape.Rectangle;
import org.openimaj.math.statistics.distribution.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.checkfraud.model.CheckImage;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckImageService.class);

	@Autowired
	private CheckImageServiceConfiguration configuration;

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

		List<byte[]> processedImages = new ArrayList<>();
		String originalFileExtension = FilenameUtils.getExtension(originalFileName);

		LOGGER.info("Analyze executed");

		CheckImage checkImage = new CheckImage();
		checkImage.setProcessedImages(processedImages);
		checkImage.setOriginalFileName(originalFileName);
		checkImage.setOriginalFileExtension(originalFileExtension);
		checkImage.setSize(size);
		checkImage.setContentType(contentType);

		//
		InputStream input = new ByteArrayInputStream(bs);
		MBFImage image = ImageUtilities.readMBF(input);
		checkImage.setColourSpace(image.getColourSpace());

		MBFImage resize = resize(image);
		drawSignatureRetangle(resize);
		checkImage.setHistogram(histogram(resize.clone()));

		//
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageUtilities.write(resize, originalFileExtension, output);

		processedImages.add(output.toByteArray());

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
		if (current.getWidth() == configuration.getCheckMaxWidth()) {
			return current;
		}

		// Shrink
		ResizeProcessor resize = new ResizeProcessor(configuration.getCheckMaxWidth());

		MBFImage tmp = new MBFImage(configuration.getCheckMaxWidth(), configuration.getCheckMaxHeight(),
				current.getColourSpace());
		tmp.fill(RGBColour.WHITE);

		return current.process(resize).normalise();

	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	private MBFImage drawSignatureRetangle(MBFImage image) {

		Rectangle rectangle = new Rectangle();

		rectangle.setBounds(configuration.getSignaturePosX(), configuration.getSignaturePosY(),
				configuration.getSignaturePosW(), configuration.getSignaturePosH());

		image.drawShape(rectangle, 3, RGBColour.BLACK);

		return image;
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	private double[] histogram(MBFImage image) {

		HistogramAnalyser analyser = new HistogramAnalyser(configuration.getHistogramSize());
		analyser.analyseImage(image.flatten());
		Histogram histogram = analyser.getHistogram();

		return histogram.values;

	}

}
