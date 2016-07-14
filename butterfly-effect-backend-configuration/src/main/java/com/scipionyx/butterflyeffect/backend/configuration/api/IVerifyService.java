package com.scipionyx.butterflyeffect.backend.configuration.api;

import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IVerifyService<T extends IConfiguration> {

	public IResponse<T> doVerify(T t);

}
