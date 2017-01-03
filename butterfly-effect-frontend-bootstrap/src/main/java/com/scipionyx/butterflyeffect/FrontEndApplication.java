
package com.scipionyx.butterflyeffect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.vaadin.annotations.Push;
import com.vaadin.shared.communication.PushMode;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */

//
@SpringBootApplication(exclude = { SessionAutoConfiguration.class })

//
@EnableDiscoveryClient

//

@Push(value = PushMode.DISABLED)
public class FrontEndApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}

}
