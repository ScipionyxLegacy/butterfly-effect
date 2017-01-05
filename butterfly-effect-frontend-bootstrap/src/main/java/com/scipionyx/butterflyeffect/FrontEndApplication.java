
package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */

//
@SpringBootApplication // (exclude = { SessionAutoConfiguration.class })

//
@EnableDiscoveryClient

//
@EnableSpringConfigured

@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)

@EnableAspectJAutoProxy()

public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

}
