
package com.scipionyx.butterflyeffect.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

}
