package com.scipionyx.butterflyeffect.backend.configuration.api;

import java.io.IOException;

import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 */
public interface IWriteService<T extends IConfiguration> {

	public IResponse<String> put(T t, boolean bakup, String salt)
			throws IOException, IllegalArgumentException, IllegalAccessException;

}
