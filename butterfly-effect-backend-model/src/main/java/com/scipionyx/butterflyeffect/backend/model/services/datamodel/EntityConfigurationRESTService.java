package com.scipionyx.butterflyeffect.backend.model.services.datamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.backend.configuration.service.AbstractConfigurationRESTService;
import com.scipionyx.butterflyeffect.model.model.datamodel.Entity;

/**
 * How to name the REST service
 * /REST/$vendor-name$/$module-name$/$function-name$
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController
@RequestMapping("/REST/scipionyx/model/entity")
public class EntityConfigurationRESTService extends AbstractConfigurationRESTService<Entity> {

	@Autowired(required = true)
	private EntityConfigurationService service;

	public EntityConfigurationService getService() {
		return service;
	}

	public void setService(EntityConfigurationService service) {
		this.service = service;
	}

}
