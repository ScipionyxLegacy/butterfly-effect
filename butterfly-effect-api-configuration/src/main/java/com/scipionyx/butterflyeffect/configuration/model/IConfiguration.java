package com.scipionyx.butterflyeffect.configuration.model;

import java.io.File;
import java.util.Date;

/**
 * 
 * 
 * 
 * @author rmendes
 *
 */
public interface IConfiguration {

	public String getId();

	public void setId(String id);

	public File getFile();

	public void setFile(File file);

	public String getFileName();

	public boolean isEncrypted();

	public void setEncrypted(boolean value);

	public void setModified(Date date);

}
