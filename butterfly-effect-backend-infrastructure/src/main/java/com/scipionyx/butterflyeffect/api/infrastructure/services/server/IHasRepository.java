package com.scipionyx.butterflyeffect.api.infrastructure.services.server;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 * @param <T>
 */
public interface IHasRepository<T> {

	CrudRepository<T, Long> getRepository();

}
