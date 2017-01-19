package com.scipionyx.butterflyeffect.backend.jobmanagement;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@EnableDiscoveryClient
@EnableAsync
//
@EnableIntegration
@IntegrationComponentScan()
public class Application extends AsyncConfigurerSupport {

	/**
	 * 
	 * TODO - this connection to Jms must have its own component, as many other
	 * modules might utilize it.
	 * 
	 * @param connectionFactory
	 * @param configurer
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// This provides all boot's default to this factory, including the
		// message converter

		configurer.configure(factory, connectionFactory);
		
		factory.setMessageConverter(messageConverter());
		// You could still override some of Boot's default if necessary.
		return factory;

	}

	/**
	 * 
	 * Serialize message content to json using TextMessage
	 * 
	 * @return
	 */
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
