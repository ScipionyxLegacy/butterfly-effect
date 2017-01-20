package com.scipionyx.butterflyeffect.api.checkfraud.model;

import java.io.Serializable;
import java.util.List;

import org.openimaj.image.colour.ColourSpace;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class CheckImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String originalFileName;

	private String originalFileExtension;

	private String source;

	private String contentType;

	private long size;

	private List<byte[]> processedImages;

	private ColourSpace colourSpace;

	private double[] histogram;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public ColourSpace getColourSpace() {
		return colourSpace;
	}

	public void setColourSpace(ColourSpace colourSpace) {
		this.colourSpace = colourSpace;
	}

	public double[] getHistogram() {
		return histogram;
	}

	public void setHistogram(double[] histogram) {
		this.histogram = histogram;
	}

	public List<byte[]> getProcessedImages() {
		return processedImages;
	}

	public void setProcessedImages(List<byte[]> processedImages) {
		this.processedImages = processedImages;
	}

	public String getOriginalFileExtension() {
		return originalFileExtension;
	}

	public void setOriginalFileExtension(String originalFileExtension) {
		this.originalFileExtension = originalFileExtension;
	}

}
