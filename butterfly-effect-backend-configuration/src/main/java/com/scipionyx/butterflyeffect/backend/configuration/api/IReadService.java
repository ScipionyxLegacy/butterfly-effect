package com.scipionyx.butterflyeffect.backend.configuration.api;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author rmendes
 *
 * @param <T>
 */
public interface IReadService<T> {

	public IResponse<List<T>> readAll() throws IOException;

}
