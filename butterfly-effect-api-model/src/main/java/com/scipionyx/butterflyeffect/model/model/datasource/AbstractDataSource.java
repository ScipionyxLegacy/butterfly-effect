package com.scipionyx.butterflyeffect.model.model.datasource;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.scipionyx.butterflyeffect.configuration.model.AbstractConfiguration;
import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractDataSource extends AbstractConfiguration implements IConfiguration {

	@NotNull
	private Type type;

	@NotNull
	@NotEmpty
	private String host;

	@Digits(fraction = 0, integer = 5)
	private int port;

	@NotNull
	@NotEmpty
	private String user;

	@NotNull
	@NotEmpty
	private String password;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
