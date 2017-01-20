package com.scipionyx.butterflyeffect.api.checkfraud.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class TrainCheckImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;

	private byte[] original;

	private byte[] analyzed;

	@NotNull
	@Min(1)
	@Max(400)
	private int signatureX = 210;

	@NotNull
	@Min(1)
	@Max(200)
	private int signatureY = 120;

	@NotNull
	@Min(1)
	@Max(400)
	private int signatureW = 190;

	@NotNull
	@Min(1)
	@Max(200)
	private int signatureH = 50;

	//

	@NotNull
	@Min(1)
	@Max(400)
	private int bottomlineX = 10;

	@NotNull
	@Min(1)
	@Max(200)
	private int bottomlineY = 10;

	@NotNull
	@Min(1)
	@Max(400)
	private int bottomlineW = 10;

	@NotNull
	@Min(1)
	@Max(200)
	private int bottomlineH = 10;

	//

	@NotNull
	@Min(1)
	@Max(400)
	private int accountOwnerX = 10;

	@NotNull
	@Min(1)
	@Max(200)
	private int accountOwnerY = 10;

	@NotNull
	@Min(1)
	@Max(400)
	private int accountOwnerW = 10;

	@NotNull
	@Min(1)
	@Max(200)
	private int accountOwnerH = 10;

	public int getSignatureX() {
		return signatureX;
	}

	public void setSignatureX(int signatureX) {
		this.signatureX = signatureX;
	}

	public int getSignatureY() {
		return signatureY;
	}

	public void setSignatureY(int signatureY) {
		this.signatureY = signatureY;
	}

	public int getSignatureW() {
		return signatureW;
	}

	public void setSignatureW(int signatureW) {
		this.signatureW = signatureW;
	}

	public int getSignatureH() {
		return signatureH;
	}

	public void setSignatureH(int signatureH) {
		this.signatureH = signatureH;
	}

	public int getBottomlineX() {
		return bottomlineX;
	}

	public void setBottomlineX(int bottomlineX) {
		this.bottomlineX = bottomlineX;
	}

	public int getBottomlineY() {
		return bottomlineY;
	}

	public void setBottomlineY(int bottomlineY) {
		this.bottomlineY = bottomlineY;
	}

	public int getBottomlineW() {
		return bottomlineW;
	}

	public void setBottomlineW(int bottomlineW) {
		this.bottomlineW = bottomlineW;
	}

	public int getBottomlineH() {
		return bottomlineH;
	}

	public void setBottomlineH(int bottomlineH) {
		this.bottomlineH = bottomlineH;
	}

	public int getAccountOwnerX() {
		return accountOwnerX;
	}

	public void setAccountOwnerX(int accountOwnerX) {
		this.accountOwnerX = accountOwnerX;
	}

	public int getAccountOwnerY() {
		return accountOwnerY;
	}

	public void setAccountOwnerY(int accountOwnerY) {
		this.accountOwnerY = accountOwnerY;
	}

	public int getAccountOwnerW() {
		return accountOwnerW;
	}

	public void setAccountOwnerW(int accountOwnerW) {
		this.accountOwnerW = accountOwnerW;
	}

	public int getAccountOwnerH() {
		return accountOwnerH;
	}

	public void setAccountOwnerH(int accountOwnerH) {
		this.accountOwnerH = accountOwnerH;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getAnalyzed() {
		return analyzed;
	}

	public void setAnalyzed(byte[] analyzed) {
		this.analyzed = analyzed;
	}

	public byte[] getOriginal() {
		return original;
	}

	public void setOriginal(byte[] original) {
		this.original = original;
	}

}
