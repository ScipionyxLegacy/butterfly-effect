package com.scipionyx.butterflyeffect.frontend.sessionmanagement;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.vaadin.spring.session.redis.VaadinSessionRewriteFilter;

/**
 * 
 * @author rmendes
 *
 */
@EnableRedisHttpSession
public class SessionManagement {

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	public Filter vaadinSessionRewriteFilter() {
		return new VaadinSessionRewriteFilter();
	}

}
