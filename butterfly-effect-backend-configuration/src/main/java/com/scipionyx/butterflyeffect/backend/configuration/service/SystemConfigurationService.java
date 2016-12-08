package com.scipionyx.butterflyeffect.backend.configuration.service;

import java.io.File;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.backend.configuration.api.IResponse;
import com.scipionyx.butterflyeffect.backend.configuration.api.IWriteService;
import com.scipionyx.butterflyeffect.backend.configuration.api.Response;
import com.scipionyx.butterflyeffect.configuration.model.SystemConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 */
@Component
public class SystemConfigurationService extends AbstractConfigurationService<SystemConfiguration>
		implements IWriteService<SystemConfiguration> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigurationService.class);

	@Autowired
	private SystemConfiguration configuration;

	private String configurationFileName;

	// @Value(value = "${bottoline.certmanager.basicfolder}")
	private String basicFolder;

	/**
	 * @throws Exception
	 * 
	 */
	@PostConstruct
	public void init() throws Exception {
		//
		calculateOrCreateBaseFolder();
		calculateOrCreateConfigurationFile();
		// Load Configuration
		readAll();

	}

	/**
	 * This function will calculate the configuration file name for the system.
	 * it will also created it if the system does not have one, initializing its
	 * basic information
	 * 
	 * @throws Exception
	 */
	private void calculateOrCreateConfigurationFile() throws Exception {

		//
		configurationFileName = basicFolder + "cfrm_cert_system.config";
		LOGGER.info("Configuration File Name > {}", configurationFileName);

		//
		//
		File configFile = new File(configurationFileName);

		// Check if the file exists, if not create, if yes load properties
		if (!configFile.exists()) {
			createDefaultFile(configFile);
		}

	}

	/**
	 * When not provided, this function will calculate the Basic Folder for the
	 * configurations and work area.
	 * 
	 * @throws Exception
	 */
	private void calculateOrCreateBaseFolder() throws Exception {

		if (basicFolder == null) {
			//
			basicFolder = System.getProperty("user.dir") + File.separator + "config" + File.separator;
			LOGGER.info("Basic Folder not provided, calculating default: {}", basicFolder);
		} else {
			// normalize config folder only to be sure it is ok
			if (!basicFolder.endsWith(File.separator)) {
				basicFolder = basicFolder + File.separator;
			}
		}

		// Test the basic folder
		File folder = new File(basicFolder);
		if (!folder.exists()) {
			FileUtils.forceMkdir(folder);
		} else if (!folder.isDirectory()) {
			throw new Exception("the basic folder provided (" + basicFolder + ") can not be utilized");
		}

	}

	/**
	 * 
	 */
	@Override
	public IResponse<SystemConfiguration> doVerify(SystemConfiguration t) {

		IResponse<SystemConfiguration> response = null;

		File configFolder = new File(configuration.getMetadataFolder());
		try {

			// Configuration Folder
			checkFolder(configFolder, "Configuration folder provided is not a folder.",
					"Configuration folder could not be created.");

			//
			File backupFolder = new File(configuration.getBackupFolder());
			checkFolder(backupFolder, "Backup folder provided is not a folder.", "Backup folder could not be created.");

			//
			File newCertUploadFolder = new File(configuration.getNewCertUploadFolder());
			checkFolder(newCertUploadFolder, "Cert Upload Folder folder provided is not a folder.",
					"Cert Upload Folder folder could not be created.");

			//
			File tempFolder = new File(configuration.getTempFolder());
			checkFolder(tempFolder, "Temp Folder folder provided is not a folder.",
					"Temp folder could not be created.");

			response = new Response<>(t);

		} catch (Exception e) {
			response = new Response<>(e);
		}

		return response;
	}

	/**
	 * This function creates the default configuration file.
	 * 
	 * @throws Exception
	 */
	private void createDefaultFile(File configFile) throws Exception {

		configFile.createNewFile();

		// populate file
		configuration = new SystemConfiguration();
		configuration.setBackupFolder(basicFolder + "backup");
		configuration.setMetadataFolder(basicFolder + "configuration");
		configuration.setDefaultKeyStorePassword("secret");
		configuration.setEncryptionPhrase("secret");
		configuration.setNewCertUploadFolder(basicFolder + "work" + File.separator + "upload");
		configuration.setTempFolder(basicFolder + "work" + File.separator + "temp");
		configuration.setFile(configFile);

		IResponse<String> doWrite = put(configuration, false, null);

		if (doWrite.getException() != null) {
			throw doWrite.getException();
		}

	}

	/**
	 * 
	 * @param folder
	 * @param msg
	 * @param msg2
	 * @throws Exception
	 */
	private void checkFolder(File folder, String msg, String msg2) throws Exception {
		if (folder.exists() && !folder.isDirectory()) {
			throw new Exception(msg);
		} else if (!folder.exists()) {
			if (!folder.mkdirs()) {
				throw new Exception(msg2);
			}
		}
	}

}
