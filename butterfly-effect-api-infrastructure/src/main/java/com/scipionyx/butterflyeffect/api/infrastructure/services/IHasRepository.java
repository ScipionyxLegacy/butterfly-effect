package com.scipionyx.butterflyeffect.api.infrastructure.services;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 * @param <T>
 */
public interface IHasRepository<ENTITY, REPOSITORY> {

	CrudRepository<ENTITY, Long> getRepository();

}
