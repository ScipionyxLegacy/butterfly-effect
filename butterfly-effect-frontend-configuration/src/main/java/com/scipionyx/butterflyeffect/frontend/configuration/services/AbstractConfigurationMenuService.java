package com.scipionyx.butterflyeffect.frontend.configuration.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractConfigurationMenuService<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigurationMenuService.class);

	protected ObjectMapper objectMapper;

	private List<T> configurations;

	/**
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	@PostConstruct
	public final void init() throws IOException, InstantiationException, IllegalAccessException {

		//
		objectMapper = new ObjectMapper();
		//
		if (configurations == null) {
			configurations = new ArrayList<>();
		}
		//
		readConfigurations();
	}

	/**
	 * This function will read the main menu configuration for all services.
	 * 
	 * @throws IOException
	 */
	protected void readConfigurations() throws IOException {
		//
		List<InputStream> configurations = loadResources("left_configuration_menu.info", null);
		//
		LOGGER.info("loading configuration menus, {} found", configurations.size());
		//
		for (InputStream inputStream : configurations) {
			T[] menuGroups = objectMapper.readValue(inputStream, getArrayJavaType());
			// Add Navigation
			Collections.addAll(getConfigurations(), menuGroups);
		}

	}

	/**
	 * Read a resource from the class path or from within a jar.<br>
	 * TODO - it seems that it is not working when distributing the software as
	 * a jar, most likely it can not find the files within jars.
	 * 
	 * @param name
	 * @param classLoader
	 * @return
	 * @throws IOException
	 */
	protected final List<InputStream> loadResources(final String name, final ClassLoader classLoader)
			throws IOException {
		final List<InputStream> list = new ArrayList<InputStream>();
		final Enumeration<URL> systemResources = (classLoader == null ? ClassLoader.getSystemClassLoader()
				: classLoader).getResources(name);
		while (systemResources.hasMoreElements()) {
			list.add(systemResources.nextElement().openStream());
		}
		return list;
	}

	public List<T> getConfigurations() {
		return configurations;
	}

	public abstract Class<T[]> getArrayJavaType();

}
