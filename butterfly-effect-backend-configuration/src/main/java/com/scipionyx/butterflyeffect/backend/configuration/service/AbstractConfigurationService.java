package com.scipionyx.butterflyeffect.backend.configuration.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.scipionyx.butterflyeffect.backend.configuration.api.IReadService;
import com.scipionyx.butterflyeffect.backend.configuration.api.IResponse;
import com.scipionyx.butterflyeffect.backend.configuration.api.IVerifyService;
import com.scipionyx.butterflyeffect.backend.configuration.api.IWriteService;
import com.scipionyx.butterflyeffect.backend.configuration.api.Response;
import com.scipionyx.butterflyeffect.configuration.model.Encrypt;
import com.scipionyx.butterflyeffect.configuration.model.EncryptField;
import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;
import com.scipionyx.butterflyeffect.configuration.model.SystemConfiguration;

/**
 * 
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 * @param <T>
 */
public abstract class AbstractConfigurationService<T extends IConfiguration>
		implements IWriteService<T>, IVerifyService<T>, IReadService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationService.class);

	protected ObjectMapper mapper;

	@Autowired
	protected SystemConfiguration systemConfiguration;

	private final Class<T> genericType;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractConfigurationService() {
		super();
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(),
				AbstractConfigurationService.class);
		// mapper = new ObjectMapper();
	}

	/**
	 * Calculate the folder for any entity
	 * 
	 * @param t
	 * @return
	 * @throws IOException
	 */
	private String calculateEntityFolderName() throws IOException {
		//
		String folderName = systemConfiguration.getMetadataFolder() + File.separator
				+ genericType.getName().replace(".", File.separator) + File.separator;

		File folder = new File(folderName);
		if (!folder.exists()) {
			FileUtils.forceMkdir(folder);
		}
		return folderName;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private File calculateEntityFolder() throws IOException {
		return new File(calculateEntityFolderName());
	}

	/**
	 * 
	 * @param t
	 * @return
	 * @throws IOException
	 */
	private File createFile(T t) throws IOException {
		String folderName = calculateEntityFolderName();
		String fileName = folderName + t.getId() + ".json";
		File json = new File(fileName);
		if (!json.exists()) {
			json.createNewFile();
		}
		return json;
	}

	/**
	 * 
	 * 
	 * 
	 * @throws IOException
	 * 
	 */
	public final IResponse<List<T>> readAll() throws IOException {
		LOGGER.debug("READ ALL function executed for the class: {}", genericType.getName());
		List<T> list = new ArrayList<>();
		// get all files from the EntityFolder
		File folder = calculateEntityFolder();
		String[] fileTypes = { "json" };
		@SuppressWarnings("unchecked")
		Collection<File> listFiles = FileUtils.listFiles(folder, fileTypes, true);
		for (File file : listFiles) {
			T t = mapper.readValue(new FileInputStream(file), genericType);
			list.add(t);
		}
		return new Response<List<T>>(list);
	}

	/**
	 * 
	 * Create a new Entity
	 * 
	 * @param t
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Override
	public final IResponse<String> put(T t, boolean backup, String salt)
			throws IOException, IllegalArgumentException, IllegalAccessException {

		IResponse<String> response = null;

		// handle Id
		if (t.getId() == null) {
			t.setId(UUID.randomUUID().toString());
		}

		if (t.getFile() == null && t.getFileName() != null) {
			t.setFile(new File(t.getFileName()));
		}

		//
		if (t.getFile() == null) {
			// we need to create a default file
			t.setFile(createFile(t));
		}

		if (backup) {
			backup(t);
		}

		try {

			// First Verify if the file is correct
			IResponse<T> verify = doVerify(t);
			if (verify == null) {
				// No verification implemented
			} else if (!verify.isVerified()) {
				return new Response<>(new Exception(verify.getException()));
			}

			//
			t.setModified(Calendar.getInstance().getTime());

			encrypt(t, salt, genericType, true);

			// After Verification Completed Saving the File
			mapper.writerWithDefaultPrettyPrinter().writeValue(t.getFile(), t);

			response = new Response<>("New entity created");

		} catch (InvocationTargetException | NoSuchMethodException e) {
			response = new Response<>(e);
		}

		return response;

	}

	/**
	 * 
	 * 
	 * 
	 * @param t
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private final void encrypt(Object t, String salt, Class<?> type, boolean encrypt_)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (type.isAnnotationPresent(Encrypt.class)) {

			Encrypt annotation = type.getAnnotation(Encrypt.class);
			if (annotation.value()) {
				if (t instanceof IConfiguration) {
					((IConfiguration) t).setEncrypted(true);
				}
				Field[] fields = type.getDeclaredFields();
				for (Field field : fields) {

					if (Modifier.isInterface(field.getModifiers())) {
						continue;
					}

					if (field.isEnumConstant()) {
						continue;
					}

					if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
						continue;
					}

					if (Modifier.isFinal(field.getModifiers())) {
						continue;
					}

					if (field.getType().equals(String.class)) {

						// Only String values are going to be encrypted

						if (field.isAnnotationPresent(EncryptField.class)) {
							EncryptField encryptFieldAnnotation = field.getAnnotation(EncryptField.class);
							if (encryptFieldAnnotation.value()) {
								// Object simpleProperty =
								// PropertyUtils.getSimpleProperty(t,
								// field.getName());
								// if (simpleProperty != null) {
								// String encryptString = null;
								// // if (encrypt_)
								// // encryptString =
								// //
								// createBasicTextEncrptor(salt).encrypt((String)
								// // simpleProperty);
								// // else
								// // encryptString =
								// //
								// createBasicTextEncrptor(salt).decrypt((String)
								// // simpleProperty);
								// PropertyUtils.setSimpleProperty(t,
								// field.getName(), encryptString);
								// }

							}
						}

					} else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
						continue;
					} else if (field.getType().equals(Date.class)) {
						continue;
					} else {
						// Object simpleProperty =
						// PropertyUtils.getSimpleProperty(t, field.getName());
						// Class<?> fieldType = field.getType();
						// encrypt(simpleProperty, salt, fieldType, encrypt_);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param salt
	 * @return
	 */
	// private BasicTextEncryptor createBasicTextEncrptor(String salt) {
	//
	// String password = (systemConfiguration.getEncryptionPhrase() != null)
	// ? systemConfiguration.getEncryptionPhrase() : "secret";
	//
	// if (salt != null)
	// password = password + salt;
	//
	// BasicTextEncryptor encryptor = new BasicTextEncryptor();
	// encryptor.setPassword(password);
	//
	// return encryptor;
	// }

	/**
	 * 
	 * @param t
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private void backup(T t) throws IOException {
		if (t.getFile() != null && t.getFile().exists()) {
			// FileUtils.copyFile(tobackup, new File(backupfolder +
			// File.separator + "cfrm_cert_system_"
			// + java.util.Calendar.getInstance().getTimeInMillis() +
			// ".config.backup"));
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public final IResponse<T> read(String id, final String salt) throws JsonParseException, IOException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		LOGGER.debug("READ function executed for the class: {}", genericType.getName());

		String calculateEntityFolderName = calculateEntityFolderName();
		File file = new File(calculateEntityFolderName + File.separator + id + ".json");

		return read(file, salt);

	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public final IResponse<T> read(File file, final String salt) throws JsonParseException, IOException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		LOGGER.debug("READ function executed for the class: {}", genericType.getName());
		// T t = (T) mapper.readValue(file, this.genericType);
		// t.setFile(file);
		// remove encryption it
		// encrypt(t, salt, genericType, false);

		// IResponse<T> response = new Response<>(t);
		return null;// response;
	}

	/**
	 * 
	 * @param t
	 * @param b
	 * @param object
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final IResponse<T> delete(T t, boolean b) throws IOException {
		LOGGER.debug("DELETE function executed for the class: {}", genericType.getName());
		if (t.getFile() != null && t.getFile().exists()) {
			t.getFile().delete();
		} else if (t.getFileName() != null) {
			t.setFile(new File(t.getFileName()));
			return delete(t, b);
		} else if (t.getId() != null) {
			String calculateEntityFolderName = calculateEntityFolderName();
			File file = new File(calculateEntityFolderName + File.separator + t.getId() + ".json");
			t.setFile(file);
			return delete(t, b);
		} else {
			LOGGER.error("DELETE function executed for the class: {}, but no file provided", genericType.getName());
		}
		return new Response(t);
	}

	/**
	 * 
	 * 
	 * 
	 * @param id
	 * @param salt
	 * @return
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public IResponse<T> read(final T t, final String salt) throws IOException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// find the file
		//
		String calculateEntityFolderName = calculateEntityFolderName();
		File file = new File(calculateEntityFolderName + File.separator + t.getId() + ".json");

		IResponse<T> read = read(file, salt);

		return read;

	}

}
