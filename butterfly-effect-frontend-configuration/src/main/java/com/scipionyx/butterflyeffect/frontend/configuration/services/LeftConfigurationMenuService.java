package com.scipionyx.butterflyeffect.frontend.configuration.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
public class LeftConfigurationMenuService extends AbstractConfigurationMenuService<LeftConfigurationMenuItem> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeftConfigurationMenuService.class);

	/**
	 * This function will read the main menu configuration for all services.
	 * 
	 * @throws IOException
	 */
	@Override
	public void readConfigurations() throws IOException {
		//
		List<InputStream> configurations = loadResources("left_configuration_menu.info", null);
		//
		LOGGER.info("loading configuration menus, {} found", configurations.size());
		//
		for (InputStream inputStream : configurations) {

			LeftConfigurationMenuItem[] menuGroups = objectMapper.readValue(inputStream,
					LeftConfigurationMenuItem[].class);

			// Add Navigation

			Collections.addAll(getConfigurations(), menuGroups);
		}

		// Sort Menu
		// Collections.sort(getConfigurations(), new
		// ConfigurationMenuGroupComparator());

	}

}
