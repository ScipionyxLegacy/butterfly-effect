package com.scipionyx.butterflyeffect.frontend.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	/**
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		LOGGER.info("Security - Load Users");

		//
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("check").password("fraud").roles("USER", "CHECK_FRAUD");
		//

	}

	/**
	 * 
	 * 
	 * (1) User must be authenticated to access any part of the application <br>
	 * (2) Login page is accessible to anybody <br>
	 * (3) Logout success page is accessible to anybody <br>
	 * (4) Create completely new session
	 * 
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable() // Use Vaadin's CSRF protection
				.authorizeRequests().anyRequest().authenticated() // (1)
				// .and().formLogin().loginPage("/login").permitAll() // (2)
				.and().formLogin().permitAll() // (2)
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logged-out").permitAll() // (3)
				.and().sessionManagement().sessionFixation().newSession(); // (4)

	}

}
