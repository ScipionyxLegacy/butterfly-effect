package com.scipionyx.butterflyeffect.frontend.model.services;

import org.springframework.stereotype.Service;

import com.scipionyx.butterflyeffect.frontend.configuration.services.AbstractClientRESTConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datasource.ORMDataSource;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Service
public class DataSourceService extends AbstractClientRESTConfigurationService<ORMDataSource> {

	private static final String requestMapping = "/REST/scipionyx/model/ormdatasource";

	@Override
	public String getRequestMapping() {
		return requestMapping;
	}

	@Override
	public Class<?> getArrayClass() {
		return ORMDataSource.class;
	}

}
