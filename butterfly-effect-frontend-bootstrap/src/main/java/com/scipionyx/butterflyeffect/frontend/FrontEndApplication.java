
package com.scipionyx.butterflyeffect.frontend;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.vaadin.spring.session.redis.VaadinSessionRewriteFilter;

/**
 * 
 * @author Renato Mendes
 *
 */

//
@SpringBootApplication

//
@Configuration
@EnableAutoConfiguration

//
@EnableDiscoveryClient

//
@EnableRedisHttpSession
public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

	@Bean
	public Filter vaadinSessionRewriteFilter() {
		return new VaadinSessionRewriteFilter();
	}

}
