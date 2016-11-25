package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.scipionyx.butterflyeffect.frontend.model.Navigation;
import com.scipionyx.butterflyeffect.frontend.model.NavigationComparator;

/**
 * 
 * This service is responsible for reading the default configuration of the
 * Navigation from all available plugins
 * 
 * 
 * @author Renato Mendes
 *
 */
@Service("butterfly-effect-frontend-ui:NavigationService")
public class NavigationService extends AbstractConfigurationService<Navigation> {

	private static final String FILE_NAME = "navigation.info";

	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationService.class);

	/**
	 * @throws IOException
	 * 
	 */
	@PostConstruct
	public void init() throws IOException {
		super.init();
	}

	/**
	 * This function will read the main menu configuration for all services.
	 * 
	 * @throws IOException
	 */
	@Override
	public void readConfigurations() throws IOException {
		//
		List<InputStream> configurations = loadResources(FILE_NAME, null);
		//
		LOGGER.info("loading navigation files, {} found", configurations.size());
		//
		for (InputStream inputStream : configurations) {

			Navigation[] navigation = getObjectMapper().readValue(inputStream, Navigation[].class);

			// init

			Collections.addAll(getConfigurations(), navigation);
		}

		// Sort Menu
		Collections.sort(getConfigurations(), new NavigationComparator());

	}

}
