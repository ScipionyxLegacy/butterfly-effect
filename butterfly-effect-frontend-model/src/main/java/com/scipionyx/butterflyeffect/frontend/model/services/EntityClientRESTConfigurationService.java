package com.scipionyx.butterflyeffect.frontend.model.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.frontend.configuration.services.AbstractClientRESTConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datamodel.Entity;

/**
 *
 *
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class EntityClientRESTConfigurationService extends AbstractClientRESTConfigurationService<Entity> {

	private static final String requestMapping = "REST/scipionyx/model/entity";

	/**
	 * 
	 */
	@Override
	public String getRequestMapping() {
		return requestMapping;
	}

	/**
	 * 
	 */
	@Override
	public Class<?> getArrayClass() {
		return Entity[].class;
	}

}
