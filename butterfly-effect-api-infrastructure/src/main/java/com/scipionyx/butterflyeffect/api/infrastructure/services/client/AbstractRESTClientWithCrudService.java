package com.scipionyx.butterflyeffect.api.infrastructure.services.client;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 */
public abstract class AbstractRESTClientWithCrudService<T> extends AbstractRESTClientService
		implements CrudRepository<T, Long> {

}
