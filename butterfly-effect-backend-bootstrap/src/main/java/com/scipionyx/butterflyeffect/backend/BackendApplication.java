package com.scipionyx.butterflyeffect.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@SpringBootApplication
@ComponentScan(lazyInit = true)

//
@EnableConfigServer
@EnableDiscoveryClient
@EnableJpaRepositories
public class BackendApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}