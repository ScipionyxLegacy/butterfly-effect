package com.scipionyx.butterflyeffect.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(lazyInit = true)
// Consul
@EnableDiscoveryClient
@EnableConfigServer
public class BackendApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}