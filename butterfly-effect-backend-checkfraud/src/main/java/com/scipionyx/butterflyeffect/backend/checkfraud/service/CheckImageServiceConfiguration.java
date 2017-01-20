package com.scipionyx.butterflyeffect.backend.checkfraud.service;

import org.springframework.stereotype.Component;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Component
public class CheckImageServiceConfiguration {

	private long signaturePosX = 210;

	private long signaturePosY = 120;

	private long signaturePosW = 190;

	private long signaturePosH = 50;

	private int histogramSize = 64;

	private int checkMaxWidth = 419;

	private int checkMaxHeight = 189;

	public long getSignaturePosX() {
		return signaturePosX;
	}

	public void setSignaturePosX(long signaturePosX) {
		this.signaturePosX = signaturePosX;
	}

	public long getSignaturePosY() {
		return signaturePosY;
	}

	public void setSignaturePosY(long signaturePosY) {
		this.signaturePosY = signaturePosY;
	}

	public long getSignaturePosW() {
		return signaturePosW;
	}

	public void setSignaturePosW(long signaturePosW) {
		this.signaturePosW = signaturePosW;
	}

	public long getSignaturePosH() {
		return signaturePosH;
	}

	public void setSignaturePosH(long signaturePosH) {
		this.signaturePosH = signaturePosH;
	}

	public int getHistogramSize() {
		return histogramSize;
	}

	public void setHistogramSize(int histogramSize) {
		this.histogramSize = histogramSize;
	}

	public int getCheckMaxWidth() {
		return checkMaxWidth;
	}

	public void setCheckMaxWidth(int checkMaxWidth) {
		this.checkMaxWidth = checkMaxWidth;
	}

	public int getCheckMaxHeight() {
		return checkMaxHeight;
	}

	public void setCheckMaxHeight(int checkMaxHeight) {
		this.checkMaxHeight = checkMaxHeight;
	}

}
