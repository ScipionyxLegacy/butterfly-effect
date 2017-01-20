package com.scipionyx.butterflyeffect.configuration.model;

import java.io.File;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class AbstractConfiguration implements IConfiguration {

	@NotNull
	private String id;

	@JsonIgnore
	private File file;

	@NotNull
	private String displayName;

	@NotNull
	@NotBlank
	private String description;

	private String fileName;

	private Date modified;

	private Date created;

	private boolean encrypted;

	private boolean active;

	public final File getFile() {
		return file;
	}

	public final void setFile(File file) {
		this.file = file;
		if (file != null)
			this.fileName = file.getAbsolutePath();
	}

	public final Date getModified() {
		return modified;
	}

	public final void setModified(Date modified) {
		this.modified = modified;
	}

	public final Date getCreated() {
		return created;
	}

	public final void setCreated(Date created) {
		this.created = created;
	}

	public final String getFileName() {
		return fileName;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getDisplayName() {
		return displayName;
	}

	public final void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final boolean isEncrypted() {
		return encrypted;
	}

	public final void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public final boolean isActive() {
		return active;
	}

	public final void setActive(boolean active) {
		this.active = active;
	}

}
