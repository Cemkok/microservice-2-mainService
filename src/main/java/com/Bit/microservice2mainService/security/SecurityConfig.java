/**
 * Spring Boot version 2.6.2
 * Build Automation Tool is Maven.
 * Java version 1.8
 */
package com.Bit.microservice2mainService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Bit.microservice2mainService.core.constants.Logging;
import com.Bit.microservice2mainService.security.jwt.JwtAuthorizationFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the web security configuration class.
 * This configuration was created by implementing the WebSecurityConfigurerAdapter interface that SpringSecurity provides.
 * <p> WebSecurityConfigurerAdapter , Provides a convenient base class for creating a {@link WebSecurityConfigurer} instance.
 * The implementation allows customization by overriding methods.</p>
 *
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   15:16:24
 * @see
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	  private static final String[] AUTH_WHITELIST = {
	 
	            // -- Swagger UI v3 (OpenAPI)
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "swagger-ui.html/**"
	            // other public endpoints of your API may be appended to this array
	    };

	  
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	/**
	 * This method configures the authorization. It constrains different paths for different roles.
	 */
	protected void configure(HttpSecurity http) throws Exception {
		log.info("[configure method(HttpSecurity http) is called ]--" + "[input parameter =" +http+ "]");

		Logging.internalLogDetail();
	  http.csrf().disable();
	  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 
	  http.authorizeRequests()
	  .antMatchers(HttpMethod.OPTIONS).permitAll()
	  .antMatchers("/api/authentication/**").permitAll().antMatchers(AUTH_WHITELIST).permitAll() //login and register pre-path
	  
	  .anyRequest().authenticated();
	  
	  http.addFilterBefore(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	  
	
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html/**",
                                   "/webjars/**","/v3/api-docs/**",
                   	            "/swagger-ui/**",
                	            "swagger-ui.html/**");
    }
	/**
	 * This method is used to set the authentication type.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("[configure method is called ]--" + "[input parameter =" +auth+ "]");

		Logging.internalLogDetail();
		
		auth.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("[passwordEncoder method is called ]--" + "[input parameter ="+"]");

		Logging.internalLogDetail();
		return  new BCryptPasswordEncoder();
	}
		
	@Bean
	public JwtAuthorizationFilter JwtAuthorizationFilter() {
		log.info("[JwtAuthorizationFilter method is called ]--" + "[input parameter = no args"+"]");

		Logging.internalLogDetail();
		
		return new JwtAuthorizationFilter();
		
	}
	
					
	
	
	@Bean
	/**
	 * This method customizes the permissions granted to paths and methods.
	 * @return  WebMvcConfigurer()
	 */
	public WebMvcConfigurer corsConfigurer() {
		log.info("[corsConfigurer method is called ]--" + "[input parameter = no args"+"]");

		Logging.internalLogDetail();
		
			return new WebMvcConfigurer() {
				
				public void addCorsMapping (CorsRegistry registry) {
					registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedOrigins("http://localhost:5555/swagger-ui/")
					.allowedMethods("*");
					
				}
			
		};
		

	}}




