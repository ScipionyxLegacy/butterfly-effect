
package com.scipionyx.butterflyeffect;

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
@SpringBootApplication()
@ComponentScan()

@Configuration()
@EnableAutoConfiguration
@EnableDiscoveryClient
public class CheckFraudTestApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CheckFraudTestApplication.class, args);
	}

}
