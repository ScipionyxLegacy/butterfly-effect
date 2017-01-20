package com.scipionyx.butterflyeffect.backend.model.services.datamodel;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.backend.configuration.api.IResponse;
import com.scipionyx.butterflyeffect.backend.configuration.service.AbstractConfigurationService;
import com.scipionyx.butterflyeffect.model.model.datamodel.Entity;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component(value = "Scipionyx.Model.EntityConfigurationService")
public class EntityConfigurationService extends AbstractConfigurationService<Entity> {

	@Override
	public IResponse<Entity> doVerify(Entity t) {
		return null;
	}

}
