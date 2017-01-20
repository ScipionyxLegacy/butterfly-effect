package com.scipionyx.butterflyeffect.configuration.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
public class SystemConfiguration extends AbstractConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.metadata.folder:''}")
	private String metadataFolder;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.encryption.phrase:''}")
	private String encryptionPhrase;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.encryption.active:false}")
	private boolean encryptionActive;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.temp.folder:''}")
	private String tempFolder;

	@Deprecated
	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.cert.upload.folder:''}")
	private String newCertUploadFolder;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.metadata.backup.folder:''}")
	private String backupFolder;

	@NotNull
	@NotBlank
	@Value(value = "${butterflyeffect.system.configuration.metadata.backup.active:false}")
	private boolean backupMetadataActive;

	@NotNull
	@NotBlank
	@Deprecated
	@Value(value = "${butterflyeffect.system.configuration.keystore.password.default:''}")
	private String defaultKeyStorePassword;

	@Value(value = "${butterflyeffect.system.configuration.displayname:''}")
	private String displayName;

	private boolean useGitHub;

	private String gitHubPath;

	private String gitHubUser;

	private String gitHubPassword;

	public String getMetadataFolder() {
		return metadataFolder;
	}

	public void setMetadataFolder(String metadataFolder) {
		this.metadataFolder = metadataFolder;
	}

	public String getEncryptionPhrase() {
		return encryptionPhrase;
	}

	public void setEncryptionPhrase(String encryptionPhrase) {
		this.encryptionPhrase = encryptionPhrase;
	}

	public String getTempFolder() {
		return tempFolder;
	}

	public void setTempFolder(String tempFolder) {
		this.tempFolder = tempFolder;
	}

	public String getNewCertUploadFolder() {
		return newCertUploadFolder;
	}

	public void setNewCertUploadFolder(String newCertUploadFolder) {
		this.newCertUploadFolder = newCertUploadFolder;
	}

	public String getBackupFolder() {
		return backupFolder;
	}

	public void setBackupFolder(String backupFolder) {
		this.backupFolder = backupFolder;
	}

	public String getDefaultKeyStorePassword() {
		return defaultKeyStorePassword;
	}

	public void setDefaultKeyStorePassword(String defaultKeyStorePassword) {
		this.defaultKeyStorePassword = defaultKeyStorePassword;
	}

	public boolean isUseGitHub() {
		return useGitHub;
	}

	public void setUseGitHub(boolean useGitHub) {
		this.useGitHub = useGitHub;
	}

	public String getGitHubPath() {
		return gitHubPath;
	}

	public void setGitHubPath(String gitHubPath) {
		this.gitHubPath = gitHubPath;
	}

	public String getGitHubUser() {
		return gitHubUser;
	}

	public void setGitHubUser(String gitHubUser) {
		this.gitHubUser = gitHubUser;
	}

	public String getGitHubPassword() {
		return gitHubPassword;
	}

	public void setGitHubPassword(String gitHubPassword) {
		this.gitHubPassword = gitHubPassword;
	}

}
