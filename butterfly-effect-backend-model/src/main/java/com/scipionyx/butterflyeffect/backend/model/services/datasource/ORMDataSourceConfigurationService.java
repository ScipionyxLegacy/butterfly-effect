package com.scipionyx.butterflyeffect.backend.model.services.datasource;

import com.scipionyx.butterflyeffect.backend.configuration.api.IResponse;
import com.scipionyx.butterflyeffect.backend.configuration.api.IVerifyService;
import com.scipionyx.butterflyeffect.backend.configuration.api.IWriteService;
import com.scipionyx.butterflyeffect.backend.configuration.api.Response;
import com.scipionyx.butterflyeffect.backend.configuration.service.AbstractConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datasource.ORMDataSource;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public class ORMDataSourceConfigurationService extends AbstractConfigurationService<ORMDataSource>
		implements IWriteService<ORMDataSource>, IVerifyService<ORMDataSource> {

	@Override
	public IResponse<ORMDataSource> doVerify(ORMDataSource t) {
		IResponse<ORMDataSource> response = new Response<>();
		return response;
	}

}
