package com.scipionyx.butterflyeffect.model.model.datasource;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.scipionyx.butterflyeffect.configuration.model.IConfiguration;

/**
 * 
 * @author Renato Mendes
 *
 */
public class ORMDataSource extends AbstractDataSource implements IConfiguration {

	@NotNull
	private Database database;

	@NotNull
	@NotEmpty
	private String databaseName;

	private boolean cluster;

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public boolean isCluster() {
		return cluster;
	}

	public void setCluster(boolean cluster) {
		this.cluster = cluster;
	}

}
