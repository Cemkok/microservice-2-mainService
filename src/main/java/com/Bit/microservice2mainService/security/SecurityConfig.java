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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Bit.microservice2mainService.security.jwt.JwtAuthorizationFilter;
import com.Bit.microservice2mainService.util.constants.Logging;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Cem Kok
 * @Date   12 Tem 2022
 * @Time   15:16:24
 * @see
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	  
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("[configure method is called ]--" + "[input parameter =" +http+ "]");

		Logging.internalLogDetail();
	  http.csrf().disable();
	  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 
	  http.authorizeRequests()
	  .antMatchers(HttpMethod.OPTIONS).permitAll()
	  .antMatchers("/api/authentication/**").permitAll() //login and register pre-path
	  
	  .anyRequest().authenticated();
	  
	  http.addFilterBefore(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	  
	
	}
	
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
	public WebMvcConfigurer corsConfigurer() {
		log.info("[corsConfigurer method is called ]--" + "[input parameter = no args"+"]");

		Logging.internalLogDetail();
		
			return new WebMvcConfigurer() {
				
				public void addCorsMapping (CorsRegistry registry) {
					registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("*");
					
				}
			
		};
		

	}}




