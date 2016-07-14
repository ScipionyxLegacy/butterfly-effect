package com.scipionyx.butterflyeffect.frontend.configuration.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.configuration.model.SystemConfiguration;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@Deprecated
public class SystemConfigurationService extends AbstractClientRESTConfigurationService<SystemConfiguration> {

	@Override
	public String getRequestMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getArrayClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
