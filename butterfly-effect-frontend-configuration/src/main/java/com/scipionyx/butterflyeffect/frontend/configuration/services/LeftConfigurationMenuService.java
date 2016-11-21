package com.scipionyx.butterflyeffect.frontend.configuration.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.configuration.model.leftmenu.LeftConfigurationMenuItem;

/**
 * TODO: This must be changed to comply with a API capable of creating such
 * extra menu on the right side of the top menu bar.
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component()
public class LeftConfigurationMenuService extends AbstractConfigurationMenuService<LeftConfigurationMenuItem> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeftConfigurationMenuService.class);

	/**
	 * 
	 */
	@Override
	public Class<LeftConfigurationMenuItem[]> getArrayJavaType() {
		return LeftConfigurationMenuItem[].class;
	}

}
