package com.scipionyx.butterflyeffect.frontend.sessionmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author rmendes
 *
 */
@EnableHazelcastHttpSession
public class SessionManagementHazelcast {

	@Bean
	public HazelcastInstance hazelcastInstance() {

		MapAttributeConfig attributeConfig = new MapAttributeConfig().setName("principalName")
				.setExtractor(PrincipalNameExtractor.class.getName());

		Config config = new Config();

		config.getMapConfig("spring:session:sessions").addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig("principalName", false));

		return Hazelcast.newHazelcastInstance(config);
	}

}
