
package com.scipionyx.butterflyeffect.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Renato Mendes
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "${butterflyeffect.system.componentscan.basepackages}", })

@Configuration
@EnableAutoConfiguration

@EnableDiscoveryClient
public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

}
