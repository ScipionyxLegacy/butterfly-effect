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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

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
			http.exceptionHandling()
					.authenticationEntryPoint(
							new LoginUrlAuthenticationEntryPoint("/login/#!butterfly-effect-frontend-security:login"))
					.accessDeniedPage("/accessDenied").and().authorizeRequests()
					.antMatchers("/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/login/**", "/error/**",
							"/accessDenied/**", "/vaadinServlet/**")
					.permitAll().antMatchers("/authorized", "/**").fullyAuthenticated();

			// http.exceptionHandling().antMatchers("/beans", "/metrics",
			// "/trace", "/pause",
			// "/restart").hasRole("ADMIN").and().formLogin();

			// http.exceptionHandling().defaultAuthenticationEntryPointFor(entryPoint,
			// preferredMatcher)

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
