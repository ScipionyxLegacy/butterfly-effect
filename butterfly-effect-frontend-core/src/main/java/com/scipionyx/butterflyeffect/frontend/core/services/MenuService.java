package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.scipionyx.butterflyeffect.frontend.model.Menu;
import com.scipionyx.butterflyeffect.frontend.model.MenuComparator;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration;
import com.scipionyx.butterflyeffect.ui.view.MenuConfiguration.Position;

/**
 * 
 * This service is responsible for reading the default configuration of the
 * menus from the plugins
 * 
 * 
 * @author Renato Mendes
 *
 */
@Service("MenuService")
public class MenuService extends AbstractConfigurationService<Menu> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

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

		// Scan for all classes under UI.VIEW that has the @MenuConfiguration
		// annotation

		
		
//		//
//		List<InputStream> configurations = loadResources("menu.info", null);
//		//
//		LOGGER.info("loading menus, {} found", configurations.size());
//		//
//		for (InputStream inputStream : configurations) {
//			Menu[] _menus = getObjectMapper().readValue(inputStream, Menu[].class);
//			Collections.addAll(getConfigurations(), _menus);
//		}
//
//		// Sort Menu
//		Collections.sort(getConfigurations(), new MenuComparator());

	}

	/**
	 * 
	 * @return
	 */
	public List<Menu> getChildren(String id) {

		List<Menu> itens = new ArrayList<Menu>();

		for (Menu menu : getConfigurations()) {
			if (menu.getParent() != null && menu.getParent().equals(id)) {
				itens.add(menu);
			}
		}

		return itens;

	}

}
