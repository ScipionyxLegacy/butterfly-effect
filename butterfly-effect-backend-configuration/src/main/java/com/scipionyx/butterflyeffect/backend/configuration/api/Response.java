package com.scipionyx.butterflyeffect.backend.configuration.api;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public class Response<T> implements IResponse<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean verified;

	private T response;

	private Exception exception;

	/**
	 * 
	 */
	public Response() {
		super();
		this.verified = true;
	}

	/**
	 * 
	 * @param exception
	 */
	public Response(Exception exception) {
		super();
		this.verified = false;
		this.exception = exception;
	}

	/**
	 * 
	 * @param exception
	 */
	public Response(T t) {
		super();
		this.verified = true;
		this.response = t;
	}

	/**
	 * 
	 * @param exception
	 */
	public Response(T t, Exception exception) {
		super();
		this.verified = false;
		this.response = t;
		this.exception = exception;

	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
