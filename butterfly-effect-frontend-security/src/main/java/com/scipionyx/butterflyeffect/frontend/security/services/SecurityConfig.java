package com.scipionyx.butterflyeffect.frontend.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
	private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

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
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	@Configuration
	@Order(2)
	public static class VaadinWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		/**
		 * 
		 */
		protected void configure(HttpSecurity http) throws Exception {

			//
			http.csrf().disable();

			//
			http.authorizeRequests().antMatchers("/VAADIN/**").permitAll();
			http.authorizeRequests().antMatchers("/#!butterfly-effect-frontend-security:login").permitAll();
			http.authorizeRequests().anyRequest().authenticated();

			// Login
			http.formLogin().permitAll();
			http.formLogin().defaultSuccessUrl("/", true);
			// http.formLogin().loginPage("/#!" + LoginView.VIEW_NAME);

			//
			http.logout().logoutUrl("/logout").logoutSuccessUrl("/logout?success").permitAll();

			//
			http.sessionManagement().sessionFixation().newSession();

		}

	}

	/**
	 * 
	 * 
	 * 
	 * @author Renato Mendes - rmendes@bottomline.com /
	 *         renato.mendes.1123@gmail.com
	 *
	 */
	@Configuration
	@Order(1)
	public static class HealthSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/health").anonymous().authorities("SYSTEM");
		}

	}

}
